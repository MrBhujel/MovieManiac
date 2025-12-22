package com.example.moviemaniac.data.di

import com.example.moviemaniac.data.repository.AllTrendingRepositoryImpl
import com.example.moviemaniac.data.repository.MovieDetailScreenRepositoryImpl
import com.example.moviemaniac.data.repository.MovieRepositoryImpl
import com.example.moviemaniac.data.repository.TvRepositoryImpl
import com.example.moviemaniac.domain.repository.AllTrendingRepository
import com.example.moviemaniac.domain.repository.MovieDetailScreenRepository
import com.example.moviemaniac.domain.repository.MovieRepository
import com.example.moviemaniac.domain.repository.TvRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        impl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun bindTvRepository(
        impl: TvRepositoryImpl
    ): TvRepository

    @Binds
    @Singleton
    abstract fun bindAllTrendingTopCard(
        impl: AllTrendingRepositoryImpl
    ): AllTrendingRepository

    @Binds
    @Singleton
    abstract fun bindMovieDetailScreen(
        impl: MovieDetailScreenRepositoryImpl
    ): MovieDetailScreenRepository

}