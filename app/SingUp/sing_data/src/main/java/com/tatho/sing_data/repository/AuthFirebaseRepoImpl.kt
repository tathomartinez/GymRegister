package com.tatho.sing_data.repository


import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.tatho.sing_domain.repository.IAuthFirebaseRepository

class AuthFirebaseRepoImpl(
    private var auth: FirebaseAuth
) : IAuthFirebaseRepository {
    override suspend fun register(email: String, password: String, callback: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                callback(true)
                Log.e("AUT", "Se creo el usuario")
            }.addOnFailureListener {
                callback(false)
                Log.e("AUT", "No se creo el usuario")
            }
    }
}