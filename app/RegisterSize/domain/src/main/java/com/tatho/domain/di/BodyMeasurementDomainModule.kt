package com.tatho.domain.di

import com.tatho.domain.repository.BodyMeasurementRepository
import com.tatho.domain.usercase.SaveBodyMeasurementSizeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class BodyMeasurementDomainModule {

    @Provides
    fun provideSaveBodyMeasurementSizeUseCase(repository: BodyMeasurementRepository): SaveBodyMeasurementSizeUseCase {
        return SaveBodyMeasurementSizeUseCase(repository)
    }
}
