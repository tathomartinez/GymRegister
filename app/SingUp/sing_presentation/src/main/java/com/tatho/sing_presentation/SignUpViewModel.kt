package com.tatho.sing_presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatho.sing_domain.usercase.IsRegisteredSessionUserCase
import com.tatho.sing_domain.usercase.SingUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: SingUpUseCase,
    private val isRegisteredSessionUserCase: IsRegisteredSessionUserCase
) : ViewModel() {
    val navigateToNextScreen = mutableStateOf(false)
    val showError = mutableStateOf(false)
    val checkboxChecked = MutableLiveData<Boolean>()
    val emailFieldState = mutableStateOf("")
    val passwordFieldState = mutableStateOf("")
    val repeatPasswordFieldState = mutableStateOf("")


    init {
        checkboxChecked.value = false
        navigateToNextScreen.value = isRegisteredSessionUserCase.invoke()
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
        repeatPasswordFieldState.value = ""
    }

    fun onRepeatPasswordFieldChanged(newValue: String) {
        repeatPasswordFieldState.value = newValue
    }

    fun register() {
        val email = emailFieldState.value
        val password = passwordFieldState.value

        if (validatePreRegister(email, password)) {
            viewModelScope.launch {
                registerUserUseCase.invoke(email, password) {
                    when (it) {
                        "99" -> {
                            showError.value = true
                            Log.e("Error", "Llego al 99")
                            viewModelScope.launch {
                                delay(5000)
                                showError.value = false
                            }
                        }
                        "00" -> {
                            navigateToNextScreen.value = true
                        }
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

    private fun validatePreRegister(email: String, password: String): Boolean {
        val repeatPassword = repeatPasswordFieldState.value
        val checkboxChecked = checkboxChecked.value

        Log.e("validatePreRegister", "email: $email, password: $password, repeatPassword: $repeatPassword, checkboxChecked: $checkboxChecked")

        return email.isNotBlank() &&
                password.isNotBlank() &&
                password == repeatPassword &&
                checkboxChecked == true
    }

}
