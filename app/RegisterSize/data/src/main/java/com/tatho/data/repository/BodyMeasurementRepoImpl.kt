package com.tatho.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.tatho.domain.model.BodyMeasurements
import com.tatho.domain.repository.BodyMeasurementRepository

class BodyMeasurementRepoImpl(
    private val dataStore: FirebaseFirestore
) : BodyMeasurementRepository {

    override suspend fun saveBodyMeasurement(
        bodyMeasurement: BodyMeasurements,
        callback:(String) -> Unit
    ) {

        val id: String
        val document = dataStore.collection("bodyMeasurements").document()
        id = document.id
        document
            .set(bodyMeasurement)
            .addOnSuccessListener {
                callback(id)
            }
            .addOnFailureListener { e -> Log.e("DB", e.stackTraceToString()) }
    }

//    private fun callback(id: String) {
//        Log.e("SAVE", "Fue guardado con exito el caso de uso $id")
//    }
}
