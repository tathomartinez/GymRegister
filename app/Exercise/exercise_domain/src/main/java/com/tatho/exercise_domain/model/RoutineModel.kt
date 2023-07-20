package com.tatho.exercise_domain.model

data class RoutineModel(val repeticiones: Int, val series: Int, val peso: Int, var userId: String = "") {
}