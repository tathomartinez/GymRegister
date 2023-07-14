package com.tatho.login_domain.repository

import com.tatho.login_domain.Credentials
import com.tatho.login_domain.model.LoginCallback

interface IAuthFirebaseLoginRepository {
    suspend fun login(credentials: Credentials, callback: LoginCallback)
}