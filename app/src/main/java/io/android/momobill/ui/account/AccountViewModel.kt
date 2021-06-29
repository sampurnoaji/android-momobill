package io.android.momobill.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.momobill.domain.entity.auth.UserInfo
import io.android.momobill.domain.usecase.auth.GetUserInfoUseCase
import io.android.momobill.domain.usecase.auth.LogoutUseCase
import io.android.momobill.util.extension.onError
import io.android.momobill.util.extension.onSuccess
import io.android.momobill.vo.ViewState
import kotlinx.coroutines.launch

class AccountViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _userInfo = MutableLiveData<ViewState<UserInfo>>()
    val userInfo = liveData { emitSource(_userInfo) }

    fun getUserInfo() {
        _userInfo.value = ViewState.Loading
        viewModelScope.launch {
            getUserInfoUseCase()
                .onSuccess { _userInfo.value = ViewState.Success(it) }
                .onError { _userInfo.value = ViewState.Error(it) }
        }
    }

    fun logout() {
        logoutUseCase()
    }
}
