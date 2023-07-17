package com.tatho.login_domain

data class Credentials(val email: Email, val password: Password)

data class Email(val value: String) {
    init {
        require(Regex("^\\S+@\\S+\\.\\S+$").matches(value)) {
            "Dirección de correo electrónico inválida"
        }
    }
}

data class Password(val value: String) {
    init {
        require(value.length >= 6) {
            "La contraseña debe tener al menos 6 caracteres"
        }
    }
}
