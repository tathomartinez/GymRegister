package com.tatho.sing_data.di

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.tatho.sing_data.repository.AuthFirebaseSingUpRepoImpl
import com.tatho.sing_domain.repository.IAuthFirebaseSingUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseAuthSingUpModel {

    @Provides
    fun provideAuthFirebaseSingUpRepository(
        firebaseAuth: FirebaseAuth,
        sharedPreferences: SharedPreferences
    ): IAuthFirebaseSingUpRepository {
        return AuthFirebaseSingUpRepoImpl(firebaseAuth, sharedPreferences)
    }
}
