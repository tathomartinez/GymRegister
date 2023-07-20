package com.tatho.exercise_domain.model

data class RoutineModel(
    val id: String,
    var userId: String? = null,
    var listExercise: MutableList<ExerciseModel> = mutableListOf(),
) 