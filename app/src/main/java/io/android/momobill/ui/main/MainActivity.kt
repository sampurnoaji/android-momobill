package io.android.momobill.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.android.momobill.databinding.ActivityMainBinding
import io.android.momobill.util.delegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.label.text = vm.getUsername()
    }
}