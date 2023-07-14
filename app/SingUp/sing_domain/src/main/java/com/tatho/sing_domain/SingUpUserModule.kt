package com.tatho.sing_domain

import android.content.SharedPreferences
import com.tatho.sing_domain.repository.IAuthFirebaseSingUpRepository
import com.tatho.sing_domain.usercase.IsRegisteredSessionUserCase
import com.tatho.sing_domain.usercase.SingUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingUpUserModule {

    @Provides
    fun provideSingUpUseCase(repository: IAuthFirebaseSingUpRepository): SingUpUseCase {
        return SingUpUseCase(repository)
    }

    @Provides
    fun provideIsRegisteredSessionUserCase(sharedPreferences: SharedPreferences): IsRegisteredSessionUserCase {
        return IsRegisteredSessionUserCase(sharedPreferences)
    }

}