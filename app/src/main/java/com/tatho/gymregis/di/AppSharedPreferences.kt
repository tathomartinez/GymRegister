package com.tatho.gymregis.di

import android.content.SharedPreferences
import javax.inject.Inject

interface AppSharedPreferences {
    fun getString(key: String, defaultValue: String): String?
    fun putString(key: String, value: String)
}

class AppSharedPreferebcesImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    AppSharedPreferences {

    override fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    override fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
}
