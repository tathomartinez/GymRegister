package com.tatho.login_domain.repository

import com.tatho.login_domain.Credentials

interface IAuthFirebaseLoginRepository {
    suspend fun login(credentials: Credentials)
}