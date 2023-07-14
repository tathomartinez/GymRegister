package com.tatho.sing_domain.repository

interface IAuthFirebaseSingUpRepository {
    suspend fun register(email: String, password: String, callback: (String) -> Unit)
}
