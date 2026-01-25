package com.example.moviemaniac.feature.mediaPlayerScreen

import com.example.moviemaniac.data.service.VideoStreamInfo

sealed class MediaPlayerState {
    object Initial: MediaPlayerState()
    object Loading: MediaPlayerState()
    data class Success(val streamInfo: VideoStreamInfo): MediaPlayerState()
    data class Error(val message: String): MediaPlayerState()
}