package com.tatho.showsize_domain.model

import java.util.*

data class BodyMeasurements(
    val chest: Int,
    val waist: Int,
    val bicep: Int,
    val gluteus: Int,
    val back: Int,
    val date: Date,
    val userId: String
)