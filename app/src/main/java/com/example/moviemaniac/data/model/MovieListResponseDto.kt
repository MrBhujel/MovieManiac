package com.example.moviemaniac.data.model

data class PopularMovieListResponseDto(
    val results: List<PopularMovieDto>? = emptyList()
)

data class NowPlayingMovieListResponseDto(
    val results: List<NowPlayingMovieDto>? = emptyList()
)

data class TopRatedMovieListResponseDto(
    val results: List<TopRatedMovieDto>? = emptyList()
)