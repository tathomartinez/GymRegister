package com.tatho.domain.repository

import com.tatho.domain.model.BodyMeasurement

interface BodyMeasurementRepository {
    suspend fun saveBodyMeasurement(bodyMeasurement: BodyMeasurement): BodyMeasurement
}