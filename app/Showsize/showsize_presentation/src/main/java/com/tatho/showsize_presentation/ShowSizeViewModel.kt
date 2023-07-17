package com.tatho.showsize_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatho.showsize_domain.model.BodyMeasurements
import com.tatho.showsize_domain.usercase.GetBodyMeasurementsListByUidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowSizeViewModel @Inject constructor(
    private val getBodyMeasurementsListByUidUseCase: GetBodyMeasurementsListByUidUseCase
) : ViewModel() {
    private val _medidas = MutableStateFlow<List<BodyMeasurements>>(emptyList())
    val medidas: StateFlow<List<BodyMeasurements>> = _medidas

    fun getShowSize() {
        viewModelScope.launch {
            val listMedidas = getBodyMeasurementsListByUidUseCase.invoke()
            _medidas.value = listMedidas
        }
    }
}