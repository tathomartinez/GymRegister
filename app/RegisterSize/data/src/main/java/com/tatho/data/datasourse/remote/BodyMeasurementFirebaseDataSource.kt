package com.tatho.data.datasourse.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.tatho.domain.model.BodyMeasurements
import kotlinx.coroutines.tasks.await
import com.tatho.common.Resource

class BodyMeasurementFirebaseDataSource(firestore: FirebaseFirestore) {
    
    private val collection = FirebaseFirestore.getInstance().collection("bodyMeasurements")

    suspend fun saveBodyMeasurements(bodyMeasurements: BodyMeasurements): Resource<Long> {
        return try {
            val document = collection.add(bodyMeasurements).await()
            Resource.Success(document.id.toLong())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}
