package com.tatho.showsize_domain.di

import android.content.SharedPreferences
import com.tatho.showsize_domain.repository.IShowSizeRepository
import com.tatho.showsize_domain.usercase.GetBodyMeasurementsListByUidUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ShowSizeDomainModule {

    @Provides
    fun provideGetBodyMeasurementsListByUidUseCase(sharedPreferences: SharedPreferences, repository: IShowSizeRepository): GetBodyMeasurementsListByUidUseCase {
        return GetBodyMeasurementsListByUidUseCase(sharedPreferences, repository)
    }

}