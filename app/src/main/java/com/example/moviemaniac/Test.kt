package com.example.moviemaniac

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.moviemaniac.feature.mediaPlayerScreen.MediaPlayerState
import com.example.moviemaniac.feature.mediaPlayerScreen.MediaPlayerViewModel

@Composable
fun TestExtractionScreen() {
    val viewModel = hiltViewModel<MediaPlayerViewModel>()
    val state by viewModel.playerState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                viewModel.extractVideoStream("https://vidsrc.to/embed/movie/550")
            }
        ) {
            Text("Test Extraction")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (val currentState = state) {
            is MediaPlayerState.Loading -> CircularProgressIndicator()
            is MediaPlayerState.Success -> {
                Text("Success!")
                Text("URL: ${currentState.streamInfo.videoUrl}")
                Text("Type: ${currentState.streamInfo.type}")
            }
            is MediaPlayerState.Error -> Text("Error: ${currentState.message}")
            else -> {}
        }
    }
}