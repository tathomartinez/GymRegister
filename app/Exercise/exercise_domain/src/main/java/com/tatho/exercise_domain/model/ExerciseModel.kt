package com.tatho.exercise_domain.model

data class ExerciseModel(
    var id: String,
    var repeticiones: Int = 0,
    var series: Int = 0,
    var peso: Int = 0,
    var name: String,
) {
}