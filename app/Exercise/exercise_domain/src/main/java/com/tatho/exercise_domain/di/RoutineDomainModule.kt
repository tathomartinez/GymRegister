package com.tatho.exercise_domain.di

import com.tatho.exercise_domain.repository.IRoutineRepository
import com.tatho.exercise_domain.usercase.SaveRoutineUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RoutineDomainModule {

    @Provides
    fun provideSaveRoutineUseCase (repository: IRoutineRepository): SaveRoutineUseCase {
        return SaveRoutineUseCase(repository)
    }
}