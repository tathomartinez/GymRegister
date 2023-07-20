package com.tatho.exercise_data.di

import android.content.SharedPreferences
import com.google.firebase.firestore.FirebaseFirestore
import com.tatho.exercise_data.repository.RoutineRepositoryImpl
import com.tatho.exercise_domain.repository.IRoutineRepository
import com.tatho.exercise_domain.usercase.SaveRoutineUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.sql.CommonDataSource

@InstallIn(SingletonComponent::class)
@Module
class RoutineDataModule {

    @Provides
    fun provideRoutineRepositoryImpl(
        dataSource: FirebaseFirestore,
        sharedPreferences: SharedPreferences
    ): IRoutineRepository {
        return RoutineRepositoryImpl(dataSource, sharedPreferences)
    }
}