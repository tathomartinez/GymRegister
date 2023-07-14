package com.tatho.sing_domain.usercase

import com.tatho.sing_domain.repository.IAuthFirebaseSingUpRepository
import javax.inject.Inject

class SingUpUseCase @Inject constructor(
    private val repository: IAuthFirebaseSingUpRepository
) {

    suspend operator fun invoke(email: String, password: String, callback: (String) -> Unit) {
        repository.register(email, password) {

            if (it == "No se creo el usuario") {
                callback("99")
                return@register
            }

            callback("00")
        }
    }
}