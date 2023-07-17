package com.tatho.domain.usercase

import android.content.SharedPreferences
import javax.inject.Inject

class GetUidFirebaseUseCase @Inject
constructor(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): String {
        return try {
            val uid = sharedPreferences.getString("Uid", "")
            uid ?: ""
        } catch (e: Exception) {
            ""
        }
    }
}