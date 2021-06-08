package io.android.momobill.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import io.android.momobill.R
import io.android.momobill.databinding.ActivityMainBinding
import io.android.momobill.util.delegate.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val navController by lazy { Navigation.findNavController(this, R.id.navHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }
}