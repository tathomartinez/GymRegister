package com.tatho.login_domain.usercase

import com.tatho.login_domain.Credentials
import com.tatho.login_domain.model.LoginCallback
import com.tatho.login_domain.repository.IAuthFirebaseLoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: IAuthFirebaseLoginRepository
) {
    suspend operator fun invoke(credentials: Credentials, callback: LoginCallback) {
        repository.login(credentials, callback)
    }
}