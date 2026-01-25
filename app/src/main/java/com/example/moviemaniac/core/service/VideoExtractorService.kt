package com.example.moviemaniac.data.service

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.util.regex.Pattern

interface VideoExtractorService {
    suspend fun extractVideoUrl(embedUrl: String): VideoStreamInfo?
}

data class VideoStreamInfo(
    val title: String,
    val videoUrl: String,
    val quality: String = "Unknown",
    val requiresDecryption: Boolean = false,
    val headers: Map<String, String> = emptyMap(),
    val type: StreamType = StreamType.UNKNOWN
)

enum class StreamType {
    HLS, DASH, MP4, UNKNOWN
}

class VideoExtractorServiceImpl : VideoExtractorService {

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Language", "en-US,en;q=0.5")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("Sec-Fetch-Dest", "document")
                .addHeader("Sec-Fetch-Mode", "navigate")
                .addHeader("Sec-Fetch-Site", "none")
                .addHeader("Sec-Fetch-User", "?1")
                .addHeader("Cache-Control", "max-age=0")
                .build()
            chain.proceed(request)
        }
        .build()

    private val gson = Gson()

    override suspend fun extractVideoUrl(embedUrl: String): VideoStreamInfo? {
        return withContext(Dispatchers.IO) {
            try {
                Log.d("VideoExtractor", "Starting extraction for: $embedUrl")

                // Step 1: Get the embed page
                val embedPage = fetchHtml(embedUrl)
                if (embedPage.isNullOrEmpty()) return@withContext null

                // Step 2: Parse HTML and extract iframe or script
                val doc = Jsoup.parse(embedPage)

                // Look for iframes first
                val iframeSrc = doc.select("iframe").first()?.attr("src")
                if (iframeSrc != null && iframeSrc.isNotEmpty()) {
                    Log.d("VideoExtractor", "Found iframe: $iframeSrc")
                    return@withContext extractFromIframe(iframeSrc)
                }

                // Look for video sources in scripts
                val scriptTags = doc.select("script")
                for (script in scriptTags) {
                    val scriptContent = script.html()

                    // Pattern for HLS streams
                    val hlsPattern = Pattern.compile("(https?:\\/\\/[^\\s'\"]+\\.m3u8[^\\s'\"]*)")
                    val hlsMatcher = hlsPattern.matcher(scriptContent)

                    if (hlsMatcher.find()) {
                        val hlsUrl = hlsMatcher.group(1)
                        Log.d("VideoExtractor", "Found HLS URL: $hlsUrl")
                        return@withContext VideoStreamInfo(
                            title = "Stream",
                            videoUrl = hlsUrl,
                            quality = "Adaptive",
                            type = StreamType.HLS
                        )
                    }

                    // Pattern for MP4 streams
                    val mp4Pattern = Pattern.compile("(https?:\\/\\/[^\\s'\"]+\\.mp4[^\\s'\"]*)")
                    val mp4Matcher = mp4Pattern.matcher(scriptContent)

                    if (mp4Matcher.find()) {
                        val mp4Url = mp4Matcher.group(1)
                        Log.d("VideoExtractor", "Found MP4 URL: $mp4Url")
                        return@withContext VideoStreamInfo(
                            title = "Stream",
                            videoUrl = mp4Url,
                            quality = "SD",
                            type = StreamType.MP4
                        )
                    }

                    // Look for JSON data
                    if (scriptContent.contains("sources") || scriptContent.contains("file")) {
                        try {
                            // Find JSON-like objects
                            val jsonPattern = Pattern.compile("\\{(?:[^{}]|\\{[^{}]*\\})*\\}")
                            val jsonMatcher = jsonPattern.matcher(scriptContent)

                            while (jsonMatcher.find()) {
                                val jsonStr = jsonMatcher.group()
                                if (jsonStr.contains("file") || jsonStr.contains("sources")) {
                                    try {
                                        val json = gson.fromJson(jsonStr, JsonObject::class.java)

                                        // Check for video file
                                        if (json.has("file")) {
                                            val fileUrl = json.get("file").asString
                                            if (fileUrl.endsWith(".m3u8") || fileUrl.endsWith(".mp4")) {
                                                return@withContext VideoStreamInfo(
                                                    title = "Stream",
                                                    videoUrl = fileUrl,
                                                    quality = json.get("label")?.asString ?: "Unknown",
                                                    type = if (fileUrl.endsWith(".m3u8")) StreamType.HLS else StreamType.MP4
                                                )
                                            }
                                        }

                                        // Check for sources array
                                        if (json.has("sources")) {
                                            val sources = json.getAsJsonArray("sources")
                                            if (sources.size() > 0) {
                                                val source = sources[0].asJsonObject
                                                val fileUrl = source.get("file").asString
                                                return@withContext VideoStreamInfo(
                                                    title = "Stream",
                                                    videoUrl = fileUrl,
                                                    quality = source.get("label")?.asString ?: "Unknown",
                                                    type = if (fileUrl.endsWith(".m3u8")) StreamType.HLS else StreamType.MP4
                                                )
                                            }
                                        }
                                    } catch (e: Exception) {
                                        Log.e("VideoExtractor", "JSON parsing error: ${e.message}")
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            Log.e("VideoExtractor", "Script parsing error: ${e.message}")
                        }
                    }
                }

                // Last resort: Check for video tags
                val videoTag = doc.select("video").first()
                if (videoTag != null) {
                    val sourceTag = videoTag.select("source").first()
                    if (sourceTag != null) {
                        val src = sourceTag.attr("src")
                        if (src.isNotEmpty()) {
                            return@withContext VideoStreamInfo(
                                title = "Stream",
                                videoUrl = src,
                                quality = sourceTag.attr("label") ?: "Unknown",
                                type = if (src.endsWith(".m3u8")) StreamType.HLS else StreamType.MP4
                            )
                        }
                    }
                }

                Log.w("VideoExtractor", "No video source found")
                null

            } catch (e: Exception) {
                Log.e("VideoExtractor", "Extraction error: ${e.message}")
                null
            }
        }
    }

    private suspend fun extractFromIframe(iframeUrl: String): VideoStreamInfo? {
        return withContext(Dispatchers.IO) {
            try {
                val iframeContent = fetchHtml(iframeUrl)
                if (iframeContent.isNullOrEmpty()) return@withContext null

                val doc = Jsoup.parse(iframeContent)

                // Look for video sources
                val scriptTags = doc.select("script")
                for (script in scriptTags) {
                    val scriptContent = script.html()

                    // Simple URL extraction patterns
                    val urlPatterns = listOf(
                        Pattern.compile("\"(https?:\\/\\/[^\\s'\"]+\\.(?:m3u8|mp4|mkv|avi))\"")
                    )

                    for (pattern in urlPatterns) {
                        val matcher = pattern.matcher(scriptContent)
                        while (matcher.find()) {
                            val url = matcher.group(1)
                            if (url.contains("video") || url.contains("stream")) {
                                val type = when {
                                    url.endsWith(".m3u8") -> StreamType.HLS
                                    url.endsWith(".mp4") -> StreamType.MP4
                                    else -> StreamType.UNKNOWN
                                }

                                return@withContext VideoStreamInfo(
                                    title = "Stream",
                                    videoUrl = url,
                                    quality = "HD",
                                    type = type
                                )
                            }
                        }
                    }
                }

                null
            } catch (e: Exception) {
                Log.e("VideoExtractor", "Iframe extraction error: ${e.message}")
                null
            }
        }
    }

    private suspend fun fetchHtml(url: String): String? {
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url(url)
                    .build()

                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    response.body?.string()
                } else {
                    null
                }
            } catch (e: Exception) {
                Log.e("VideoExtractor", "Fetch error: ${e.message}")
                null
            }
        }
    }
}