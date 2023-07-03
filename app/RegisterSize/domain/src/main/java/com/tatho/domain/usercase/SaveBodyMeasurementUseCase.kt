package com.tatho.domain.usercase

import com.tatho.domain.model.BodyMeasurement
import com.tatho.domain.repository.BodyMeasurementRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveBodyMeasurementUseCase(private val repository: BodyMeasurementRepository) {

    operator fun invoke(bodyMeasurement: BodyMeasurement): Flow<Resource<BodyMeasurement>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.saveBodyMeasurement(bodyMeasurement)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }

}


sealed class Resource<T> {
    class Loading<T>() : Resource<T>()
    class Success<T>(val data: T?) : Resource<T>()
    class Error<T>(val message: String, val data: T? = null) : Resource<T>()
}