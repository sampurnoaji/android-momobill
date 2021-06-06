package io.android.momobill.domain.repository

import io.android.momobill.data.params.LoginParams
import io.android.momobill.data.params.RegisterParams
import io.android.momobill.domain.entity.LoginData
import io.android.momobill.vo.LoadResult

interface AuthRepository {
    suspend fun login(params: LoginParams): LoadResult<LoginData>
    suspend fun register(params: RegisterParams): LoadResult<Boolean>

    fun saveUserData(username: String)
    fun getUsername(): String
}