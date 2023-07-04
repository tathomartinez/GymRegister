package com.tatho.domain.repository

import com.tatho.domain.entites.BodyMeasurement

interface BodyMeasurementRepository {
    suspend fun saveBodyMeasurement(bodyMeasurement: BodyMeasurement): Long
}