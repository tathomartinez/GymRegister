package com.tatho.logout_data.di

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.tatho.logout_data.reposiroty.AuthFirebaseLogoutRepositoryImpl
import com.tatho.logout_domain.repository.IAuthFirebaseLogoutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseAuthLogOutModel {
    @Provides
    fun provideAuthFirebaseLogoutRepository(
        firebaseAuth: FirebaseAuth,
        sharedPreferences: SharedPreferences
    ): IAuthFirebaseLogoutRepository {
        return AuthFirebaseLogoutRepositoryImpl(firebaseAuth, sharedPreferences)
    }

}