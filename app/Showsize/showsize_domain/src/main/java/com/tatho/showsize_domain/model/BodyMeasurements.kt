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

fun BodyMeasurements.toDatosList(): List<Datos> {
    return listOf(
        Datos("Chest", chest.toFloat()),
        Datos("Waist", waist.toFloat()),
        Datos("Bicep", bicep.toFloat())
    )
}

//fun mapBodyMeasurementsToDatosList(bodyMeasurementsList: List<BodyMeasurements>): List<Datos> {
//    return bodyMeasurementsList.map { bodyMeasurement ->
//
////        Datos("Bicep", bodyMeasurement.bicep.toFloat(),
////        Datos("Waist", bodyMeasurement.waist.toFloat(),
////        Datos("Chest", bodyMeasurement.chest.toFloat(),
////        Datos("Back", bodyMeasurement.back.toFloat(),
////        Datos("Gluteus", bodyMeasurement.gluteus.toFloat())
////        )
////        )
////        )
//
////    )
////    }
//}
