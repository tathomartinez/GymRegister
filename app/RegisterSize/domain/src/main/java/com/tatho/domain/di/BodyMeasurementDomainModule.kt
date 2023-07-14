package com.tatho.domain.di

import android.content.SharedPreferences
import com.tatho.domain.repository.BodyMeasurementRepository
import com.tatho.domain.usercase.SaveBodyMeasurementSizeUseCase
import com.tatho.domain.usercase.UpdateRegisterTodayUserCase
import com.tatho.domain.usercase.ValidateRegisterTodayUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class BodyMeasurementDomainModule {

    @Provides
    fun provideSaveBodyMeasurementSizeUseCase(repository: BodyMeasurementRepository): SaveBodyMeasurementSizeUseCase {
        return SaveBodyMeasurementSizeUseCase(repository)
    }

    @Provides
    fun provideValidateRegisterTodayUserCase(sharedPreferences: SharedPreferences): ValidateRegisterTodayUserCase {
        return ValidateRegisterTodayUserCase(sharedPreferences)
    }

    @Provides
    fun provideUpdateRegisterTodayUserCase(sharedPreferences: SharedPreferences): UpdateRegisterTodayUserCase {
        return UpdateRegisterTodayUserCase(sharedPreferences)
    }
}
