package com.tatho.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RegisterSizeDataModule {

    //TODO PROVIDERS SIZE FROM FIREBASE
    @Provides
    fun provideString(): String {
        return ""
    }
}