package com.example.moviemaniac.data.di

import com.example.moviemaniac.data.service.VideoExtractorService
import com.example.moviemaniac.data.service.VideoExtractorServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideVideoExtractorService(): VideoExtractorService {
        return VideoExtractorServiceImpl()
    }
}