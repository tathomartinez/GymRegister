package com.tatho.logout_domain.repository

interface IAuthFirebaseLogoutRepository {
    suspend fun logout()
}