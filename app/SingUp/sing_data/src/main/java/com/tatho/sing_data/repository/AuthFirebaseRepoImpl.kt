package com.tatho.sing_data.repository


import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.tatho.sing_domain.repository.IAuthFirebaseRepository

class AuthFirebaseRepoImpl(
    private var auth: FirebaseAuth
) : IAuthFirebaseRepository {
    override suspend fun register(email: String, password: String, callback: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                it.user?.uid?.let { uid ->
                    callback(uid)
                }
                Log.e("AUT", "Se creo el usuario")
            }.addOnFailureListener {
                callback("No se creo el usuario")
                Log.e("AUT", "No se creo el usuario")
            }
    }
}