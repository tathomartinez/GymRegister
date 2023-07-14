package com.tatho.logout_domain

import com.tatho.logout_domain.repository.IAuthFirebaseLogoutRepository
import com.tatho.logout_domain.usercase.LogOutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LogOutModule {
    @Provides
    fun provideLogOutUseCase(authFirebaseLogoutRepository: IAuthFirebaseLogoutRepository): LogOutUseCase {
        return LogOutUseCase(authFirebaseLogoutRepository)
    }
}