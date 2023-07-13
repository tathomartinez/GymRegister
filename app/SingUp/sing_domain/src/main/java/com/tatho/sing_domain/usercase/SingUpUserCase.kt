package com.tatho.sing_domain.usercase

import android.util.Log
import com.tatho.sing_domain.repository.IAuthFirebaseRepository
import javax.inject.Inject

class SingUpUserCase @Inject constructor(
    private val repository: IAuthFirebaseRepository) {

    suspend operator fun invoke(email: String, password: String) {
        repository.register(email, password){
            Log.e("SingUpUserCase", it.toString())
        }
    }
}