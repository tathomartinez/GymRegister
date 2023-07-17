package com.tatho.domain.usercase

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import javax.inject.Inject

class UpdateRegisterTodayUserCase @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke() {
        try {
            val currentDate = LocalDate.now().toString() // Obtén la fecha actual en formato de cadena de texto
            sharedPreferences.edit().putString("regisToday", currentDate).apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}