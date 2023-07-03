package com.tatho.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "body_measurements")
data class BodyMeasurement(
    val chest: Float,
    val waist: Float,
    val bicep: Float,
    val glute: Float,
    val back: Float
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}