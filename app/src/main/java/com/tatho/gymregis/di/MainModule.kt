package com.tatho.gymregis.di

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.tatho.data.room.BodyMeasurementDao
import com.tatho.gymregis.room.AppDatabase
import com.tatho.menu_data.room.MenuItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MainModule {

    @Provides
    @Singleton
    fun provideGymRegisterDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideBodyMeasurementDao(appDatabase: AppDatabase): BodyMeasurementDao {
        return appDatabase.getBodyMeasurementDao()
    }

    @Provides
    fun provideMenuItemDao(appDatabase: AppDatabase): MenuItemDao {
        return appDatabase.getItemMenuDao()
    }

    //firebase
    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

}

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}