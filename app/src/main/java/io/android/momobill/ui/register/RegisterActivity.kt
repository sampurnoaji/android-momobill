package io.android.momobill.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import io.android.momobill.R
import io.android.momobill.data.params.RegisterParams
import io.android.momobill.databinding.ActivityRegisterBinding
import io.android.momobill.util.delegate.viewBinding
import io.android.momobill.util.extension.disable
import io.android.momobill.util.extension.enable
import io.android.momobill.util.extension.gone
import io.android.momobill.util.extension.isValidEmail
import io.android.momobill.util.extension.isValidPhone
import io.android.momobill.util.extension.showAlertDialog
import io.android.momobill.util.extension.visible
import io.android.momobill.vo.LoadResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityRegisterBinding::inflate)
    private val vm by viewModel<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        setupInputFieldListener()
        observeRegisterResult()

        binding.btnRegister.setOnClickListener {
            validateInput()
        }
    }

    private fun observeRegisterResult() {
        vm.registerResult.observe(this) {
            when (it) {
                is LoadResult.Loading -> {
                    binding.pgbRegister.visible()
                    binding.btnRegister.disable()
                }
                is LoadResult.Success -> {
                    showAlertDialog(
                        "",
                        getString(R.string.register_successful),
                        getString(R.string.close)
                    ) {
                        finish()
                    }
                }
                is LoadResult.Error -> {
                    binding.pgbRegister.gone()
                    binding.btnRegister.enable()
                    showAlertDialog(
                        "",
                        getString(R.string.register_failed),
                        getString(R.string.close)
                    )
                }
                else -> {
                }
            }
        }
    }

    private fun setupInputFieldListener() {
        binding.etName.doOnTextChanged { _, _, _, _ ->
            binding.etName.error = null
        }
        binding.etPhone.doOnTextChanged { _, _, _, _ ->
            binding.etPhone.error = null
        }
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.etEmail.error = null
        }
        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            binding.etPassword.error = null
        }
        binding.etPasswordConfirm.doOnTextChanged { _, _, _, _ ->
            with(binding.etPasswordConfirm) {
                error = null

                val password = binding.etPassword.text.toString().trim()
                val passwordConfirm = text.toString().trim()
                if (password != passwordConfirm) error = getString(R.string.not_equal_password)
            }
        }
    }

    private fun validateInput() {
        val name = binding.etName.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val passwordConfirm = binding.etPasswordConfirm.text.toString().trim()

        if (name.isEmpty()) {
            binding.etName.error = getString(R.string.empty_username)
            return
        }
        if (phone.isEmpty()) {
            binding.etPhone.error = getString(R.string.empty_phone)
            return
        }
        if (!phone.isValidPhone()) {
            binding.etPhone.error = getString(R.string.invalid_phone)
            return
        }
        if (email.isEmpty()) {
            binding.etEmail.error = getString(R.string.empty_email)
            return
        }
        if (!email.isValidEmail()) {
            binding.etEmail.error = getString(R.string.invalid_email)
            return
        }
        if (password.isEmpty()) {
            binding.etPassword.error = getString(R.string.empty_password)
            return
        }
        if (passwordConfirm.isEmpty()) {
            binding.etPasswordConfirm.error = getString(R.string.empty_password)
            return
        }
        vm.register(getRegisterParams())
    }

    private fun getRegisterParams(): RegisterParams {
        val name = binding.etName.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        return RegisterParams(name, phone, email, password)
    }
}
