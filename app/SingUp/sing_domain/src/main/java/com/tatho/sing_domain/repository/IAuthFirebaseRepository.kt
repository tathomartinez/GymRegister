package com.tatho.sing_domain.repository

interface IAuthFirebaseRepository {
    suspend fun register(email: String, password: String, callback: (Boolean) -> Unit)
}
