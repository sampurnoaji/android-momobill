package io.android.momobill.ui.main

import androidx.lifecycle.ViewModel
import io.android.momobill.domain.usecase.GetUsernameUseCase

class MainViewModel(private val getUsernameUseCase: GetUsernameUseCase) : ViewModel() {
    fun getUsername() = getUsernameUseCase()
}