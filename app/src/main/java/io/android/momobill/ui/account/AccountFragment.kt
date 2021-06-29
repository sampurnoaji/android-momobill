package io.android.momobill.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.android.momobill.R
import io.android.momobill.databinding.FragmentAccountBinding
import io.android.momobill.ui.login.LoginActivity
import io.android.momobill.util.delegate.viewBinding
import io.android.momobill.util.extension.gone
import io.android.momobill.util.extension.start
import io.android.momobill.util.extension.visible
import io.android.momobill.vo.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment(R.layout.fragment_account) {

    private val binding by viewBinding(FragmentAccountBinding::bind)
    private val vm by viewModel<AccountViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUserInfoResult()
        vm.getUserInfo()

        binding.btnLogout.setOnClickListener {
            vm.logout()
            start<LoginActivity>()
            requireActivity().finish()
        }
    }

    private fun observeUserInfoResult() {
        vm.userInfo.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pgbLoading.visible()
                }
                is ViewState.Success -> {
                    binding.pgbLoading.gone()
                    binding.tvName.text = it.data.fullName
                    binding.tvPhone.text = it.data.phone
                    binding.tvEmail.text = it.data.email
                }
                is ViewState.Error -> {
                    binding.pgbLoading.gone()
                }
            }
        }
    }
}
