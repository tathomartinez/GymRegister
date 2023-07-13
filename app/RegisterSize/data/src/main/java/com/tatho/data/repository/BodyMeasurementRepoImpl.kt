package com.tatho.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.tatho.domain.model.BodyMeasurements
import com.tatho.domain.repository.BodyMeasurementRepository
import kotlinx.coroutines.tasks.await

class BodyMeasurementRepoImpl(
    private val dataStore: FirebaseFirestore
) : BodyMeasurementRepository {

        override suspend fun saveBodyMeasurement(
        bodyMeasurement: BodyMeasurements
    ) {

        var id = ""
        val document = dataStore.collection("bodyMeasurements").document()
        id = document.id
        document
            .set(bodyMeasurement)
            .addOnSuccessListener {
                Log.e("SAVE", "Fue guardado con exito el caso de uso")
            }
            .addOnFailureListener { e -> Log.e("DB", e.stackTraceToString()) }
    }
//    override suspend fun saveBodyMeasurement(bodyMeasurement: BodyMeasurements) {
//        try {
//            val document = dataStore.collection("bodyMeasurements").document()
//            val id = document.id
//            document.set(bodyMeasurement).await() // Esperar a que se complete la operación
//        } catch (e: Exception) {
//            Log.e("DB", e.stackTraceToString())
//            throw e // Lanzar la excepción para propagarla hacia arriba
//        }
//    }
//    override suspend fun saveBodyMeasurement(bodyMeasurement: BodyMeasurements): Boolean {
//        return try {
//            val document = dataStore.collection("bodyMeasurements").document()
//            val id = document.id
//            document.set(bodyMeasurement).await()
//            true
//        } catch (e: Exception) {
//            Log.e("DB", e.stackTraceToString())
//            false
//        }
//    }

}
