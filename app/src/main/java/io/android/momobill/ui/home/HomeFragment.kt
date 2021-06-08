package io.android.momobill.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.android.momobill.R
import io.android.momobill.databinding.FragmentHomeBinding
import io.android.momobill.util.delegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val vm by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}