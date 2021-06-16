package io.android.momobill.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.android.momobill.R
import io.android.momobill.ui.login.LoginActivity
import io.android.momobill.ui.main.MainActivity
import io.android.momobill.util.extension.hideSystemUI
import io.android.momobill.util.extension.start
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val vm by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(1000)

            if (vm.isUserLoggedIn()) start<MainActivity>()
            else start<LoginActivity>()
            finish()
        }
    }
}
