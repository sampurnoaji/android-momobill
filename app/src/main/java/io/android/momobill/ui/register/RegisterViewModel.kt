package io.android.momobill.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.momobill.data.params.auth.RegisterParams
import io.android.momobill.domain.usecase.auth.RegisterUseCase
import io.android.momobill.util.extension.onError
import io.android.momobill.util.extension.onSuccess
import io.android.momobill.vo.ViewState
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {

    private val _registerResult = MutableLiveData<ViewState<Boolean>>()
    val registerResult = liveData { emitSource(_registerResult) }

    fun register(params: RegisterParams) {
        _registerResult.value = ViewState.Loading
        viewModelScope.launch {
            registerUseCase(params)
                .onSuccess { _registerResult.value = ViewState.Success(it) }
                .onError { _registerResult.value = ViewState.Error(it) }
        }
    }
}
