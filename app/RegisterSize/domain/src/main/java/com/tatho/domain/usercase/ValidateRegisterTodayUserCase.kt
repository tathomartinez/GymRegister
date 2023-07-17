package com.tatho.domain.usercase

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import javax.inject.Inject

class ValidateRegisterTodayUserCase @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
     @RequiresApi(Build.VERSION_CODES.O)
     operator fun invoke(callBack:(Boolean) -> Unit ) {
        try {
            val storedDate = sharedPreferences.getString("regisToday", null)
            val currentDate = LocalDate.now().toString()
            callBack(currentDate == storedDate)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

