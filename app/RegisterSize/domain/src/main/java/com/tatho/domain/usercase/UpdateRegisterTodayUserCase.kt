package com.tatho.domain.usercase

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class UpdateRegisterTodayUserCase @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke() {
        try {
            val register = sharedPreferences.edit().putBoolean("regisToday", true).apply()
            Log.e("SAVE", "F $register")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
