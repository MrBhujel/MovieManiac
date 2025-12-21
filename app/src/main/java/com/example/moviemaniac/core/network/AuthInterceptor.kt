package com.example.moviemaniac.core.network

import okhttp3.Interceptor
import okhttp3.Response
import com.example.moviemaniac.BuildConfig

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        // Ensure token is not null or empty
        val token = BuildConfig.TMDB_READ_TOKEN.trim()
        if (token.isEmpty()) {
            throw IllegalStateException("TMDB_READ_TOKEN is empty! Check your BuildConfig or gradle.properties")
        }

        val request = original.newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${BuildConfig.TMDB_READ_TOKEN}"
            )
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}