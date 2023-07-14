package com.tatho.sing_domain.usercase

import android.content.SharedPreferences
import com.tatho.sing_domain.repository.IAuthFirebaseRepository
import javax.inject.Inject

class SingUpUserCase @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val repository: IAuthFirebaseRepository
) {

    suspend operator fun invoke(email: String, password: String, callback: (String) -> Unit) {
        repository.register(email, password) {

            if (it == "No se creo el usuario") {
                callback("99")
                return@register
            }

            sharedPreferences.edit().putString("Uid", it).apply()
            callback("00")
        }
    }
}