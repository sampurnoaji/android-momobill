package io.android.momobill.data.source.auth

import android.content.SharedPreferences
import io.android.momobill.util.SharedPreferenceConstant

class AuthLocalDataSource(private val sharedPreferences: SharedPreferences) {

    fun saveUserData(username: String) {
        sharedPreferences.edit()
            .putString(SharedPreferenceConstant.KEY_USERNAME, username)
            .apply()
    }

    fun getUsername(): String {
        return sharedPreferences.getString(SharedPreferenceConstant.KEY_USERNAME, "") ?: ""
    }
}