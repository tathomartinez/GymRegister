package com.tatho.exercise_data.repository

import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.tatho.exercise_domain.model.RoutineModel
import com.tatho.exercise_domain.repository.IRoutineRepository

class RoutineRepositoryImpl(
    private val dataSource: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences
    ) :
    IRoutineRepository {
    override suspend fun saveRoutine(routine: RoutineModel, callback: (Boolean) -> Unit) {
        val document = dataSource.collection("routine").document()
        val uid = sharedPreferences.getString("Uid", "").toString()
        routine.userId = uid
        Log.e("SAVE", "$routine")
        document
            .set(routine)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

}
