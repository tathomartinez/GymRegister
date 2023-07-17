package com.tatho.login_presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatho.login_domain.Credentials
import com.tatho.login_domain.Email
import com.tatho.login_domain.Password
import com.tatho.login_domain.model.LoginCallback
import com.tatho.login_domain.usercase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _emailFieldState = mutableStateOf("")
    val emailFieldState: State<String> = _emailFieldState

    private val _passwordFieldState = mutableStateOf("")
    val passwordFieldState: State<String> = _passwordFieldState

    private val _loginResult = MutableStateFlow<LoginResult?>(null)
    val loginResult: StateFlow<LoginResult?> = _loginResult

    fun onEmailFieldChanged(email: String) {
        _emailFieldState.value = email
    }

    fun onPasswordFieldChanged(password: String) {
        _passwordFieldState.value = password
    }

    fun login() {
        viewModelScope.launch {
            val email = emailFieldState.value
            val password = passwordFieldState.value

            try {
                loginUseCase.invoke(Credentials(
                    email = Email(email),
                    password = Password(password)
                ),
                    object : LoginCallback {
                        override fun onLoginSuccess() {
                            _loginResult.value = LoginResult.Success
                            Log.e("Se llego al login", "onLoginSuccess")
                        }

                        override fun onLoginFailure(exception: Exception) {
                            _loginResult.value = LoginResult.Failure(exception)
                        }
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

}