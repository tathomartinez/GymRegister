package com.tatho.sing_domain

import android.content.SharedPreferences
import com.tatho.sing_domain.repository.IAuthFirebaseRepository
import com.tatho.sing_domain.usercase.IsRegisteredSessionUserCase
import com.tatho.sing_domain.usercase.SingUpUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingUpUserModule {

    @Provides
    fun provideSingUpUserCase(repository: IAuthFirebaseRepository, sharedPreferences: SharedPreferences): SingUpUserCase {
        return SingUpUserCase(sharedPreferences, repository)
    }

    @Provides
    fun provideIsRegisteredSessionUserCase(sharedPreferences: SharedPreferences): IsRegisteredSessionUserCase {
        return IsRegisteredSessionUserCase(sharedPreferences)
    }

}