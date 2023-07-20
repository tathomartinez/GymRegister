package com.tatho.exercise_domain.repository

import com.tatho.exercise_domain.model.RoutineModel

interface IRoutineRepository {
    suspend fun saveRoutine(routine: RoutineModel, callback: (Boolean) -> Unit)
}
