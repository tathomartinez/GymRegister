package com.tatho.login_data.repository

import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.tatho.login_domain.Credentials
import com.tatho.login_domain.repository.IAuthFirebaseLoginRepository

class AuthFirebaseLoginRepositoryImpl(
    private val auth: FirebaseAuth,
    private val sharedPreferences: SharedPreferences
) : IAuthFirebaseLoginRepository {
    override suspend fun login(credentials: Credentials) {
        auth.signInWithEmailAndPassword(
            credentials.email.value,
            credentials.password.value
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("LOG","Se logeo perrro")
                // El inicio de sesión se realizó correctamente
                // Aquí puedes realizar cualquier acción adicional necesaria después del inicio de sesión exitoso
            } else {
                Log.e("LOG","No se logeo perrro")
                // Ocurrió un error durante el inicio de sesión
                val exception = task.exception
                // Puedes manejar el error de acuerdo a tus necesidades, por ejemplo, mostrar un mensaje de error al usuario
            }
        }
    }
}