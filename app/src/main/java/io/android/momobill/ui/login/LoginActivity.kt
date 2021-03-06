package io.android.momobill.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import io.android.momobill.R
import io.android.momobill.data.params.auth.LoginParams
import io.android.momobill.databinding.ActivityLoginBinding
import io.android.momobill.ui.main.MainActivity
import io.android.momobill.ui.register.RegisterActivity
import io.android.momobill.util.delegate.viewBinding
import io.android.momobill.util.extension.disable
import io.android.momobill.util.extension.enable
import io.android.momobill.util.extension.gone
import io.android.momobill.util.extension.start
import io.android.momobill.util.extension.visible
import io.android.momobill.vo.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private val vm: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupInputFieldListener()
        observeLoginResult()

        binding.btnLogin.setOnClickListener {
            validateInput()
        }
        binding.btnRegister.setOnClickListener {
            start<RegisterActivity>()
        }
    }

    private fun observeLoginResult() {
        vm.loginData.observe(this) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pgbLogin.visible()
                    binding.btnLogin.disable()
                }
                is ViewState.Success -> {
                    start<MainActivity>()
                    finish()
                }
                is ViewState.Error -> {
                    binding.pgbLogin.gone()
                    binding.btnLogin.enable()
                }
            }
        }
    }

    private fun setupInputFieldListener() {
        binding.etUsername.doOnTextChanged { _, _, _, _ ->
            binding.etUsername.error = null
        }
        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            binding.etPassword.error = null
        }
    }

    private fun validateInput() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (username.isEmpty()) {
            binding.etUsername.error = getString(R.string.empty_username)
            return
        }
        if (password.isEmpty()) {
            binding.etPassword.error = getString(R.string.empty_password)
            return
        }
        vm.login(getLoginParams())
    }

    private fun getLoginParams(): LoginParams {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        return LoginParams(username, password)
    }
}
