package com.tatho.login_data.di

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.tatho.login_data.repository.AuthFirebaseLoginRepositoryImpl
import com.tatho.login_domain.repository.IAuthFirebaseLoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseAuthLoginOutModel {
    @Provides
    fun provideAuthFirebaseLogoutRepository(
        firebaseAuth: FirebaseAuth,
        sharedPreferences: SharedPreferences
    ): IAuthFirebaseLoginRepository {
        return AuthFirebaseLoginRepositoryImpl(firebaseAuth, sharedPreferences)
    }

}