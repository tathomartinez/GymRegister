package com.tatho.gymregis.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("appPrefernece", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideRegisTodayString(sharedPreferences: SharedPreferences): AppSharedPreferences {
        return AppSharedPreferebcesImpl(sharedPreferences)
    }

}
