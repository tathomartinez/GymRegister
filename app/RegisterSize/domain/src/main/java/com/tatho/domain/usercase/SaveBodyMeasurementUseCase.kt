package com.tatho.domain.usercase

import android.util.Log
import com.tatho.common.Resource
import com.tatho.domain.model.BodyMeasurements
import com.tatho.domain.repository.BodyMeasurementRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveBodyMeasurementSizeUseCase(
    private val repository: BodyMeasurementRepository
) {

    suspend operator fun invoke(bodyMeasurement: BodyMeasurements){
        try {
            repository.saveBodyMeasurement(bodyMeasurement)
            Log.e("SAVE", "Fue invocado el caso de uso")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
