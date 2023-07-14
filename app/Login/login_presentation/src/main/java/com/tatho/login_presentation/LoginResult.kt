package com.tatho.login_presentation

sealed class LoginResult {
    object Success : LoginResult()
    data class Failure(val exception: Exception) : LoginResult()
}
