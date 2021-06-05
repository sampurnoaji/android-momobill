package io.android.momobill.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.android.momobill.databinding.ActivityLoginBinding
import io.android.momobill.ui.main.MainActivity
import io.android.momobill.utils.start
import io.android.momobill.utils.viewbinding.viewBinding

class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            start<MainActivity>()
            finish()
        }
    }
}