package io.android.momobill.data.mapper

import io.android.momobill.abstraction.Mapper
import io.android.momobill.data.dto.auth.UserInfoResponse
import io.android.momobill.domain.entity.UserInfo

class UserInfoResponseMapper : Mapper<UserInfoResponse, UserInfo>() {

    override fun invoke(dto: UserInfoResponse): UserInfo {
        return UserInfo(
            email = dto.email.orEmpty(),
            fullName = dto.fullName.orEmpty(),
            phone = dto.phone.orEmpty(),
            userId = dto.userId.orEmpty(),
            username = dto.username.orEmpty()
        )
    }
}
