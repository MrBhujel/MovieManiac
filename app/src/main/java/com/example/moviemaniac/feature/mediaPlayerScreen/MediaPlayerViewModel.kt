package com.example.moviemaniac.feature.mediaPlayerScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemaniac.data.service.VideoExtractorService
import com.example.moviemaniac.data.service.VideoExtractorServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaPlayerViewModel @Inject constructor() : ViewModel() {

    private val videoExtractor: VideoExtractorService = VideoExtractorServiceImpl()

    private val _playerState = MutableStateFlow<MediaPlayerState>(MediaPlayerState.Initial)
    val playerState: StateFlow<MediaPlayerState> = _playerState.asStateFlow()

    fun extractVideoStream(embedUrl: String) {
        viewModelScope.launch {
            _playerState.value = MediaPlayerState.Loading

            try {
                val streamInfo = videoExtractor.extractVideoUrl(embedUrl)

                if (streamInfo != null) {
                    _playerState.value = MediaPlayerState.Success(streamInfo)
                } else {
                    _playerState.value = MediaPlayerState.Error("Could not extract video stream")
                }
            } catch (e: Exception) {
                _playerState.value = MediaPlayerState.Error("Error: ${e.message}")
            }
        }
    }

    fun resetState() {
        _playerState.value = MediaPlayerState.Initial
    }
}