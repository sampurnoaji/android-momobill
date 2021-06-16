package io.android.momobill.domain.usecase

import io.android.momobill.abstraction.UseCase
import io.android.momobill.domain.entity.UserInfo
import io.android.momobill.domain.repository.AuthRepository
import io.android.momobill.vo.LoadResult

class GetUserInfoUseCase(private val repository: AuthRepository) : UseCase<UseCase.None, LoadResult<UserInfo>>() {

    override suspend fun invoke(params: None): LoadResult<UserInfo> {
        return repository.getUserInfo()
    }
}
