package com.tatho.data.repository

import com.tatho.data.network.IBodyMeasurementService
import com.tatho.data.room.BodyMeasurementDao
import com.tatho.domain.model.BodyMeasurement
import com.tatho.domain.repository.BodyMeasurementRepository

class BodyMeasurementRepoImpl(
    private val bodyMeasurementService: IBodyMeasurementService,
    private val bodyMeasurementDao: BodyMeasurementDao
) : BodyMeasurementRepository {
    override suspend fun saveBodyMeasurement(bodyMeasurement: BodyMeasurement): BodyMeasurement {
        return bodyMeasurementDao.insert(bodyMeasurement)
    }

}