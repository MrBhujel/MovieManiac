package com.example.moviemaniac.data.model

data class AiringTodayTvListResponseDto(
    val results: List<AiringTodayTvDto>? = emptyList()
)

data class OnTheAirTvListResponseDto(
    val results: List<OnTheAirTvDto>? = emptyList()
)

data class PopularTvListResponseDto(
    val results: List<PopularTvDto>? = emptyList()
)

data class TopRatedTvListResponseDto(
    val results: List<TopRatedTvDto>? = emptyList()
)