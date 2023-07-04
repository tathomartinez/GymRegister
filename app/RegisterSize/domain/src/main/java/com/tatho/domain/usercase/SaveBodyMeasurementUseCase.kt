package com.tatho.domain.usercase

import com.tatho.common.Resource
import com.tatho.domain.entites.BodyMeasurement
import com.tatho.domain.repository.BodyMeasurementRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveBodyMeasurementUseCase(private val repository: BodyMeasurementRepository) {

    operator fun invoke(bodyMeasurement: BodyMeasurement): Flow<Resource<Long>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.saveBodyMeasurement(bodyMeasurement)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }
}