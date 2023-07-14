package com.tatho.logout_domain.usercase

import com.tatho.logout_domain.repository.IAuthFirebaseLogoutRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: IAuthFirebaseLogoutRepository
) {
    suspend operator fun invoke() {
        repository.logout()
    }
}