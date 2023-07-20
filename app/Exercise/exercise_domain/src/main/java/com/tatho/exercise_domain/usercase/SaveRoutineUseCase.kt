package com.tatho.exercise_domain.usercase

import android.util.Log
import com.tatho.exercise_domain.model.RoutineModel
import com.tatho.exercise_domain.repository.IRoutineRepository

class SaveRoutineUseCase(
    private val repository: IRoutineRepository
) {
    suspend operator fun invoke(routine: RoutineModel, callback: (Boolean) -> Unit) {
        try {
            repository.saveRoutine(routine, callback)
            Log.e("SAVE", "Fue invocado el caso de uso")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}