package com.tatho.domain.di

import com.tatho.domain.repository.BodyMeasurementRepository
import com.tatho.domain.usercase.SaveBodyMeasurementUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class BodyMeasurementDomainModule {

    @Provides
    fun provideSaveBodyMeasurementUseCase(repository: BodyMeasurementRepository): SaveBodyMeasurementUseCase {
        return SaveBodyMeasurementUseCase(repository)
    }
}
