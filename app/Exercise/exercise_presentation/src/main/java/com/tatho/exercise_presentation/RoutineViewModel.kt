package com.tatho.exercise_presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatho.exercise_domain.model.RoutineModel
import com.tatho.exercise_domain.usercase.SaveRoutineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineViewModel @Inject constructor(
    private val saveRoutineUseCase: SaveRoutineUseCase,
) : ViewModel() {
    // Crea los flujos (Flow) para repeticiones, series y peso
    private val _repeticionesState = MutableStateFlow(0)
    private val _seriesState = MutableStateFlow(0)
    private val _pesoState = MutableStateFlow(0)
    private val _error = MutableStateFlow("")
    private val _success = MutableStateFlow(false)
    val error: StateFlow<String> = _error.asStateFlow()
    val success: StateFlow<Boolean> = _success.asStateFlow()

    // Proporciona funciones de cambio para repeticiones, series y peso
    fun onRepeticionesChange(newValue: Int) {
        _repeticionesState.value = newValue
    }

    fun onSeriesChange(newValue: Int) {
        _seriesState.value = newValue
    }

    fun onWeightChange(newValue: Int) {
        _pesoState.value = newValue
    }

    fun onIsRoutineActiveChanged() {

        if (validateExercise()) {
            viewModelScope.launch {
                _error.value = "Ninguna magnitud puede ser 0"
                delay(3000L)
                _error.value = ""
            }
            return
        }

        viewModelScope.launch {
            val routineModel =
                RoutineModel(_repeticionesState.value, _seriesState.value, _pesoState.value)
            saveRoutineUseCase.invoke(routineModel) {
                _success.value = it
                viewModelScope.launch {
                    delay(3000L)
                    _success.value = false

                    Log.e("RoutineViewModel", "onIsRoutineActiveChanged")
                }
            }


            Log.e("RoutineViewModel", "onIsRoutineActiveChanged")
            Log.e("RoutineViewModel", " rep ${_repeticionesState.value}")
            Log.e("RoutineViewModel", " series ${_seriesState.value}")
            Log.e("RoutineViewModel", " peso ${_pesoState.value}")


        }
    }

    private fun validateExercise() =
        _pesoState.value == 0 || _seriesState.value == 0 || _repeticionesState.value == 0

    // Exponer los flujos (Flow) como StateFlow
    val repeticionesState: StateFlow<Int> get() = _repeticionesState
    val seriesState: StateFlow<Int> get() = _seriesState
    val pesoState: StateFlow<Int> get() = _pesoState


}