package com.tatho.exercise_presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatho.exercise_domain.model.ExerciseModel
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
    private val _itemsList = MutableStateFlow<MutableList<ExerciseModel>>(mutableListOf())
    val error: StateFlow<String> = _error.asStateFlow()
    val success: StateFlow<Boolean> = _success.asStateFlow()
    val itemsList: StateFlow<MutableList<ExerciseModel>> = _itemsList.asStateFlow()

    var rotine = RoutineModel(id = "0001")

    init {
        _itemsList.value.add(
            ExerciseModel(id="001",name = "Sentadilla")
        )
        _itemsList.value.add(
            ExerciseModel(id="002",name = "Push app")
        )
    }
    //TODO LOGICA PARA TENER UN ID UNICO


    fun onIsRoutineActiveChanged() {

        if (!isValidateRoutine()) {
            viewModelScope.launch {
                _error.value = "Ninguna magnitud puede ser 0"
                delay(3000L)
                _error.value = ""
            }
            return
        }

        viewModelScope.launch {
            Log.e("RoutineViewModel", "$rotine")
            saveRoutineUseCase.invoke(rotine) {
                _success.value = it
                viewModelScope.launch {
                    delay(3000L)
                    _success.value = false
                    Log.e("RoutineViewModel", "onIsRoutineActiveChanged")
                }
            }
        }
    }

    private fun isValidateRoutine() = rotine.listExercise.isNotEmpty()

    fun onItemChange(item: ExerciseModel) {
        val existingItem = rotine.listExercise.find { it.id == item.id }
        if (existingItem == null) {
            rotine.listExercise.add(item)
        } else {
            val index = rotine.listExercise.indexOf(existingItem)
            rotine.listExercise[index] = item
        }
    }

}