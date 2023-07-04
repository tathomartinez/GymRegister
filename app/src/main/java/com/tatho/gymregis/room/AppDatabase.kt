package com.tatho.gymregis.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tatho.data.room.BodyMeasurementDao
import com.tatho.menu_data.room.MenuItemDao
import com.tatho.domain.entites.BodyMeasurement
import com.tatho.menu_domain.entities.MenuItem

@Database(entities = [BodyMeasurement::class, MenuItem::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "GymRegis")
                .fallbackToDestructiveMigration()
                .createFromAsset(databaseFilePath = "databases/gymresgisterdatabase.db")
                .build()
        }
    }

    abstract fun getBodyMeasurementDao(): BodyMeasurementDao

    abstract fun getItemMenuDao(): MenuItemDao
}