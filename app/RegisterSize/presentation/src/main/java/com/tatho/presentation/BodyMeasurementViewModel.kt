package com.tatho.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatho.domain.model.BodyMeasurements
import com.tatho.domain.usercase.SaveBodyMeasurementSizeUseCase
import com.tatho.domain.usercase.UpdateRegisterTodayUserCase
import com.tatho.domain.usercase.ValidateRegisterTodayUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BodyMeasurementViewModel @Inject constructor(
    private val saveBodyMeasurementSizeUserCase: SaveBodyMeasurementSizeUseCase,
    private val validateRegisterTodayUserCase: ValidateRegisterTodayUserCase,
    private val updateRegisterTodayUserCase: UpdateRegisterTodayUserCase
) : ViewModel() {
    val chestValue = mutableStateOf(0)
    val waistValue = mutableStateOf(0)
    val bicepValue = mutableStateOf(0)
    val gluteusValue = mutableStateOf(0)
    val backValue = mutableStateOf(0)
    val showError = mutableStateOf(false)
    val showSuccess = mutableStateOf(false)

    fun save() {
        val chest = chestValue.value
        val waist = waistValue.value
        val bicep = bicepValue.value
        val gluteus = gluteusValue.value
        val back = backValue.value

        val bodyMeasurements =
            BodyMeasurements(chest, waist, bicep, gluteus, back, date = getCurrentDate())
        validateRegisterTodayUserCase.invoke() { isRegistToday ->

            if (!isRegistToday) {
                viewModelScope.launch {
                    saveBodyMeasurementSizeUserCase.invoke(bodyMeasurement = bodyMeasurements) { success ->
                        showSuccess.value = true
                        updateRegisterTodayUserCase.invoke()
                        viewModelScope.launch {
                            delay(5000)
                            showSuccess.value = false
                        }
                    }
                }
            } else {
                showError.value = true
                viewModelScope.launch {
                    delay(5000)
                    showError.value = false
                }
            }
        }
    }

    fun getCurrentDate(): Date {
        return Calendar.getInstance().time
    }
}

