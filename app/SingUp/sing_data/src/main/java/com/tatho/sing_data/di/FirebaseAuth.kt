package com.tatho.sing_data.di

import com.tatho.sing_data.repository.AuthFirebaseRepoImpl
import com.tatho.sing_domain.repository.IAuthFirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Module
@InstallIn(SingletonComponent::class)
class FirebaseAuthModel {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    fun provideAuthFirebaseRepo(firebaseAuth: FirebaseAuth): IAuthFirebaseRepository {
        return AuthFirebaseRepoImpl(firebaseAuth)
    }
}
