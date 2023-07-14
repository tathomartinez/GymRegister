package com.tatho.login_domain.usercase

import com.tatho.login_domain.Credentials
import com.tatho.login_domain.repository.IAuthFirebaseLoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: IAuthFirebaseLoginRepository
){
    suspend operator fun invoke(credentials: Credentials) {
        repository.login(credentials)
    }
}