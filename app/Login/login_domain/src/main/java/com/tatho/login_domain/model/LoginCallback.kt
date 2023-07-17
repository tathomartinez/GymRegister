package com.tatho.login_domain.model

interface LoginCallback {
    fun onLoginSuccess()
    fun onLoginFailure(exception: Exception)
}