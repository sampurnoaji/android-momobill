package io.android.momobill.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.momobill.data.params.RegisterParams
import io.android.momobill.domain.usecase.auth.RegisterUseCase
import io.android.momobill.vo.LoadResult
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {

    private val _registerResult = MutableLiveData<LoadResult<Boolean>>()
    val registerResult = liveData { emitSource(_registerResult) }

    fun register(params: RegisterParams) {
        _registerResult.value = LoadResult.Loading
        viewModelScope.launch {
            _registerResult.value = registerUseCase(params)
        }
    }
}
