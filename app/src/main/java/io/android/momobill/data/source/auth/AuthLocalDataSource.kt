package io.android.momobill.data.source.auth

import android.content.SharedPreferences
import io.android.momobill.domain.entity.UserInfo
import io.android.momobill.util.SharedPreferenceConstant

class AuthLocalDataSource(private val sharedPreferences: SharedPreferences) {

    fun saveUserInfo(userInfo: UserInfo) {
        sharedPreferences.edit()
            .putString(SharedPreferenceConstant.KEY_USER_ID, userInfo.userId)
            .putString(SharedPreferenceConstant.KEY_USERNAME, userInfo.username)
            .putString(SharedPreferenceConstant.KEY_FULLNAME, userInfo.fullName)
            .putString(SharedPreferenceConstant.KEY_PHONE, userInfo.phone)
            .putString(SharedPreferenceConstant.KEY_EMAIL, userInfo.email)
            .apply()
    }

    fun isUserLoggedIn(): Boolean {
        val userId = sharedPreferences.getString(SharedPreferenceConstant.KEY_USER_ID, "")
        return !userId.isNullOrEmpty()
    }

    fun clearUserInfo() {
        sharedPreferences.edit()
            .putString(SharedPreferenceConstant.KEY_USER_ID, "")
            .putString(SharedPreferenceConstant.KEY_USERNAME, "")
            .putString(SharedPreferenceConstant.KEY_FULLNAME, "")
            .putString(SharedPreferenceConstant.KEY_PHONE, "")
            .putString(SharedPreferenceConstant.KEY_EMAIL, "")
            .apply()
    }
}
