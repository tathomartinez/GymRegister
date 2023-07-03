package com.tatho.domain.repository

interface BodyMeasurementRepository {
    suspend fun getBodyMeasurement(): String
}