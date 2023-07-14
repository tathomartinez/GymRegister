package com.tatho.logout_data.reposiroty

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.tatho.logout_domain.repository.IAuthFirebaseLogoutRepository

class AuthFirebaseLogoutRepositoryImpl(
    private var auth: FirebaseAuth,
    private val sharedPreferences: SharedPreferences
) : IAuthFirebaseLogoutRepository {
    override suspend fun logout() {
        auth.signOut()
        sharedPreferences.edit().putString("Uid", "").apply()
    }
}