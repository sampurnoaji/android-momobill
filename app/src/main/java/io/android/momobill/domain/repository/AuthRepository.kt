package io.android.momobill.domain.repository

import io.android.momobill.domain.entity.LoginData
import io.android.momobill.vo.LoadResult

interface AuthRepository {
    suspend fun login(username: String, password: String): LoadResult<LoginData>

    fun saveUserData(username: String)
    fun getUsername(): String
}