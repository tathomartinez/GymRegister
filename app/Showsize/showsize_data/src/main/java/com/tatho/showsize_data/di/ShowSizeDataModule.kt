package com.tatho.showsize_data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.tatho.showsize_data.repository.ShowSizeRepositoryImpl
import com.tatho.showsize_domain.repository.IShowSizeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ShowSizeDataModule {

    @Provides
    fun provideShowSizeRepository(dataSource: FirebaseFirestore): IShowSizeRepository {
        return ShowSizeRepositoryImpl(dataSource)
    }
}