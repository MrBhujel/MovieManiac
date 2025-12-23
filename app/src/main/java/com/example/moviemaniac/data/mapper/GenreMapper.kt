package com.example.moviemaniac.data.mapper

import com.example.moviemaniac.data.model.GenreDto
import com.example.moviemaniac.domain.model.Genres

fun GenreDto.toDomain(): Genres {
    return Genres(
        id = id,
        name = name
    )
}