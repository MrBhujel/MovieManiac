package com.example.moviemaniac.feature.mediaPlayerScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviemaniac.feature.mediaPlayerScreen.components.VideoPlayerScreen

@Composable
fun MediaPlayerScreen(videoUrl: String) {
    VideoPlayerScreen(
        videoUrl = videoUrl,
        modifier = Modifier.fillMaxSize()
    )
}