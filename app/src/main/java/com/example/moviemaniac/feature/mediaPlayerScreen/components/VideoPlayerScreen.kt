package com.example.moviemaniac.feature.mediaPlayerScreen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun VideoPlayerScreen(
    videoUrl: String,
    modifier: Modifier = Modifier
) {
    MediaPlayer(
        videoUrl = videoUrl,
        modifier = modifier.fillMaxSize()
    )
}