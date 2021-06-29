package io.android.momobill.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.momobill.data.params.LoginParams
import io.android.momobill.domain.entity.auth.LoginData
import io.android.momobill.domain.usecase.auth.LoginUseCase
import io.android.momobill.util.extension.onError
import io.android.momobill.util.extension.onSuccess
import io.android.momobill.vo.ViewState
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginData = MutableLiveData<ViewState<LoginData>>()
    val loginData = liveData { emitSource(_loginData) }

    fun login(params: LoginParams) {
        _loginData.value = ViewState.Loading
        viewModelScope.launch {
            loginUseCase(params)
                .onSuccess { _loginData.value = ViewState.Success(it) }
                .onError { _loginData.value = ViewState.Error(it) }
        }
    }
}
