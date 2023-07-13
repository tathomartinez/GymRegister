package com.tatho.sing_domain.usercase

import android.content.SharedPreferences
import javax.inject.Inject

class IsRegisteredSessionUserCase @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): Boolean {
        return sharedPreferences.getString("Uid", "") != ""
    }
}