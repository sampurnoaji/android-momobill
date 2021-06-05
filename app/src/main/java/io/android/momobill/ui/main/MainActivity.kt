package io.android.momobill.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.android.momobill.databinding.ActivityMainBinding
import io.android.momobill.ui.login.LoginViewModel
import io.android.momobill.utils.viewbinding.viewBinding
import io.android.momobill.vo.LoadResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val vm: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        vm.getMovies()
        observeMovies()
    }

    private fun observeMovies() {
        vm.movies.observe(this) {
            when(it) {
                is LoadResult.Loading -> {

                }
                is LoadResult.Success -> {

                }
                is LoadResult.Error -> {

                }
                else -> {}
            }
        }
    }
}