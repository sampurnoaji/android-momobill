package io.android.momobill.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.momobill.abstraction.UseCase
import io.android.momobill.domain.entity.UserInfo
import io.android.momobill.domain.usecase.GetUserInfoUseCase
import io.android.momobill.domain.usecase.LogoutUseCase
import io.android.momobill.vo.LoadResult
import kotlinx.coroutines.launch

class AccountViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _userInfo = MutableLiveData<LoadResult<UserInfo>>()
    val userInfo = liveData { emitSource(_userInfo) }

    fun getUserInfo() {
        _userInfo.value = LoadResult.Loading
        viewModelScope.launch {
            _userInfo.value = getUserInfoUseCase(UseCase.None)
        }
    }

    fun logout() {
        logoutUseCase()
    }
}