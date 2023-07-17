package com.tatho.showsize_domain.repository

import com.tatho.showsize_domain.model.BodyMeasurements

interface IShowSizeRepository {
    suspend fun getBodyMeasurements(uid: String): List<BodyMeasurements>
}