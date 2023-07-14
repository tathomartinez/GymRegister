package com.tatho.login_domain.di

import com.tatho.login_domain.repository.IAuthFirebaseLoginRepository
import com.tatho.login_domain.usercase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Provides
    fun provideLoginUseCase(repository: IAuthFirebaseLoginRepository): LoginUseCase {
        return LoginUseCase(repository)
    }
}