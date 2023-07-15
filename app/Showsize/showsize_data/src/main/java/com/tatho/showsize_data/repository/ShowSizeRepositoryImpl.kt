package com.tatho.showsize_data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.tatho.showsize_domain.model.BodyMeasurements
import com.tatho.showsize_domain.repository.IShowSizeRepository
import kotlinx.coroutines.tasks.await
import java.util.*

class ShowSizeRepositoryImpl(
    private val dataStore: FirebaseFirestore
) : IShowSizeRepository {
        override suspend fun getBodyMeasurements(uid: String): List<BodyMeasurements> {
            try {
                val querySnapshot = dataStore.collection("bodyMeasurements")
                    .whereEqualTo("userId", uid)
                    .get()
                    .await()

                Log.e("TAG", "getBodyMeasurements: $uid")
                Log.e("TAG", "getBodyMeasurements: ${querySnapshot.documents.size}")

                val bodyMeasurementsList = mutableListOf<BodyMeasurements>()
                for (document in querySnapshot) {
                    val chest = document.getLong("chest")?.toInt() ?: 0
                    val waist = document.getLong("waist")?.toInt() ?: 0
                    val bicep = document.getLong("bicep")?.toInt() ?: 0
                    val gluteus = document.getLong("gluteus")?.toInt() ?: 0
                    val back = document.getLong("back")?.toInt() ?: 0
                    val date = document.getDate("date") ?: Date()

                    val bodyMeasurements = BodyMeasurements(
                        chest = chest,
                        waist = waist,
                        bicep = bicep,
                        gluteus = gluteus,
                        back = back,
                        date = date,
                        userId = uid
                    )
                    bodyMeasurementsList.add(bodyMeasurements)
                }

                return bodyMeasurementsList
            } catch (e: Exception) {
                e.printStackTrace()
                return emptyList()
            }
        }
    }
