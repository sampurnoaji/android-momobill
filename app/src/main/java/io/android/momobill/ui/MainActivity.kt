package io.android.momobill.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.android.momobill.R
import io.android.momobill.vo.LoadResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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