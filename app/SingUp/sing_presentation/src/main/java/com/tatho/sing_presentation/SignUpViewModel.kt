package com.tatho.sing_presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatho.sing_domain.usercase.SingUpUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: SingUpUserCase
) : ViewModel() {
    val checkboxChecked = MutableLiveData<Boolean>()
    val emailFieldState = mutableStateOf("")
    val passwordFieldState = mutableStateOf("")

    init {
        checkboxChecked.value = false
    }

    fun onCheckboxSelected() {
        // TODO: Implementar el evento cuando se selecciona/deselecciona el checkbox
    }

    fun onCheckboxCheckedChange(checked: Boolean) {
        checkboxChecked.value = checked
    }

    fun onEmailFieldChanged(newValue: String) {
        emailFieldState.value = newValue
    }

    fun onPasswordFieldChanged(newValue: String) {
        passwordFieldState.value = newValue
    }

    fun register() {
        val email = emailFieldState.value
        val password = passwordFieldState.value
        viewModelScope.launch {
            registerUserUseCase.invoke(email, password)
        }
    }
}
