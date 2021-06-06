package io.android.momobill.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.momobill.domain.entity.LoginData
import io.android.momobill.domain.entity.LoginParams
import io.android.momobill.domain.usecase.LoginUseCase
import io.android.momobill.vo.LoadResult
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginData = MutableLiveData<LoadResult<LoginData>>()
    val loginData = liveData { emitSource(_loginData) }

    fun login(params: LoginParams) {
        _loginData.value = LoadResult.Loading
        viewModelScope.launch {
            _loginData.value = loginUseCase(params)
        }
    }
}