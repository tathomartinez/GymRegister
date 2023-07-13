package com.tatho.domain.usercase

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class ValidateRegisterTodayUserCase @Inject constructor(

    private val sharedPreferences: SharedPreferences
) {
     operator fun invoke(callBack:(Boolean) -> Unit ) {
        try {
            val register = sharedPreferences.getBoolean("regisToday", false)
            callBack(register)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

