package com.tatho.domain.repository

import com.tatho.common.Resource
import com.tatho.domain.model.BodyMeasurements

interface BodyMeasurementRepository {
    suspend fun saveBodyMeasurement(
        bodyMeasurement: BodyMeasurements
    )
}
