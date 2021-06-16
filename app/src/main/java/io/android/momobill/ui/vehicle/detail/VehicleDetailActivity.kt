package io.android.momobill.ui.vehicle.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.android.momobill.databinding.ActivityVehicleDetailBinding
import io.android.momobill.util.delegate.viewBinding

class VehicleDetailActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityVehicleDetailBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}
