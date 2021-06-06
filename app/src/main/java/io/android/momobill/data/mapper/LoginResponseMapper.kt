package io.android.momobill.data.mapper

import io.android.momobill.abstraction.Mapper
import io.android.momobill.data.dto.auth.LoginResponse
import io.android.momobill.domain.entity.LoginData

class LoginResponseMapper : Mapper<LoginResponse, LoginData>() {

    override fun invoke(dto: LoginResponse): LoginData {
        return LoginData(
            email = dto.email.orEmpty(),
            fullName = dto.fullName.orEmpty(),
            phone = dto.phone.orEmpty(),
            status = dto.status ?: false,
            userId = dto.userId.orEmpty(),
            username = dto.username.orEmpty()
        )
    }
}