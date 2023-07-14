package com.tatho.login_data.repository

import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.tatho.login_domain.Credentials
import com.tatho.login_domain.model.LoginCallback
import com.tatho.login_domain.repository.IAuthFirebaseLoginRepository

class AuthFirebaseLoginRepositoryImpl(
    private val auth: FirebaseAuth,
    private val sharedPreferences: SharedPreferences
) : IAuthFirebaseLoginRepository {
    override suspend fun login(credentials: Credentials, callback: LoginCallback) {
        auth.signInWithEmailAndPassword(
            credentials.email.value,
            credentials.password.value
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                val uid = user?.uid
                Log.e("LOG", "Se logeo perrro")
                sharedPreferences.edit().putString("Uid", uid).apply()
                callback.onLoginSuccess()
            } else {
                Log.e("LOG", "No se logeo perrro")
                val exception = task.exception
                exception?.let {
                    callback.onLoginFailure(exception)
                }
            }
        }
    }
}