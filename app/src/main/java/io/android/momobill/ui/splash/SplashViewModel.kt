package io.android.momobill.ui.splash

import androidx.lifecycle.ViewModel
import io.android.momobill.domain.usecase.CheckUserLoggedInStatusUseCase

class SplashViewModel(private val checkUserLoggedInStatusUseCase: CheckUserLoggedInStatusUseCase) : ViewModel() {

    fun isUserLoggedIn() = checkUserLoggedInStatusUseCase()
}