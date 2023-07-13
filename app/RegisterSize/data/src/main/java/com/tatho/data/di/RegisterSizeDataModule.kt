package com.tatho.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.tatho.data.repository.BodyMeasurementRepoImpl
import com.tatho.domain.repository.BodyMeasurementRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {


    @Provides
    fun provideBodyMeasurementRepository(dataSource: FirebaseFirestore): BodyMeasurementRepository {
        return BodyMeasurementRepoImpl(dataSource)
    }
}