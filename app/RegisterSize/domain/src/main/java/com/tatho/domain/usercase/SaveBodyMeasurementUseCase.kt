package com.tatho.domain.usercase

import android.util.Log
import com.tatho.domain.model.BodyMeasurements
import com.tatho.domain.repository.BodyMeasurementRepository

class SaveBodyMeasurementSizeUseCase(
    private val repository: BodyMeasurementRepository
) {
    //Todo agregar callback de error
    suspend operator fun invoke(bodyMeasurement: BodyMeasurements, callback: (String) -> Unit) {
        try {
            repository.saveBodyMeasurement(bodyMeasurement, callback)
            Log.e("SAVE", "Fue invocado el caso de uso")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
