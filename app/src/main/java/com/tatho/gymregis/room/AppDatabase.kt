package com.tatho.gymregis.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tatho.data.room.BodyMeasurementDao
import com.tatho.domain.BodyMeasurement

@Database(entities = [BodyMeasurement::class,], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
                .fallbackToDestructiveMigration().build()
        }
    }

    abstract fun getNewsDao(): BodyMeasurementDao
}