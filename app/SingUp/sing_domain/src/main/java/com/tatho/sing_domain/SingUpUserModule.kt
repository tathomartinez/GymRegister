package com.tatho.sing_domain

import com.tatho.sing_domain.repository.IAuthFirebaseRepository
import com.tatho.sing_domain.usercase.SingUpUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingUpUserModule {
    @Provides
    fun provideSingUpUserCase(repository: IAuthFirebaseRepository): SingUpUserCase {
        return SingUpUserCase(repository)
    }
}