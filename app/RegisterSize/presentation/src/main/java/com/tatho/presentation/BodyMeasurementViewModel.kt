package com.tatho.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatho.common.Resource
import com.tatho.domain.model.BodyMeasurements
import com.tatho.domain.usercase.SaveBodyMeasurementSizeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BodyMeasurementViewModel @Inject constructor(
    private val saveBodyMeasurementSizeUserCase: SaveBodyMeasurementSizeUseCase,
) : ViewModel() {
    val chestValue = mutableStateOf(0)
    val waistValue = mutableStateOf(0)
    val bicepValue = mutableStateOf(0)
    val gluteusValue = mutableStateOf(0)
    val backValue = mutableStateOf(0)

    fun guardar() {
        // Aquí puedes acceder a los valores de los campos y realizar las acciones necesarias
        viewModelScope.launch {
            val chest = chestValue.value
            val waist = waistValue.value
            val bicep = bicepValue.value
            val gluteus = gluteusValue.value
            val back = backValue.value

            val bodyMeasurements = BodyMeasurements(chest, waist, bicep, gluteus, back)
            Log.e("SAVE", "antes de llamar al caso")
            saveBodyMeasurementSizeUserCase.invoke(bodyMeasurement = bodyMeasurements)
        }
    }
}

