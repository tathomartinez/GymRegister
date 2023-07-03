package com.tatho.data.repository

import com.tatho.data.network.IBodyMeasurementService
import com.tatho.data.room.BodyMeasurementDao
import com.tatho.domain.repository.BodyMeasurementRepository

class BodyMeasurementService(
    private val bodyMeasurementService: IBodyMeasurementService,
    private val bodyMeasurementDao: BodyMeasurementDao
) : BodyMeasurementRepository {
    override suspend fun getBodyMeasurement(): String {
        return "estas son tus medidas"
    }
}