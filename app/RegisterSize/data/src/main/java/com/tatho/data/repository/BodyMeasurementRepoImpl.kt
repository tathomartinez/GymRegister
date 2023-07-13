package com.tatho.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.tatho.domain.model.BodyMeasurements
import com.tatho.domain.repository.BodyMeasurementRepository

class BodyMeasurementRepoImpl(
    private val dataStore: FirebaseFirestore
) : BodyMeasurementRepository {

    override suspend fun saveBodyMeasurement(
        bodyMeasurement: BodyMeasurements,
        callback: (Boolean) -> Unit
    ) {

        val document = dataStore.collection("bodyMeasurements").document()
        document
            .set(bodyMeasurement)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

}
