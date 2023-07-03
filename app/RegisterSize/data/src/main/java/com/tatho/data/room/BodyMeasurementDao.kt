package com.tatho.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.tatho.domain.model.BodyMeasurement

@Dao
interface BodyMeasurementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bodyMeasurement: BodyMeasurement):BodyMeasurement
}