package io.android.momobill.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.android.momobill.databinding.ActivityMainBinding
import io.android.momobill.util.viewbinding.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}