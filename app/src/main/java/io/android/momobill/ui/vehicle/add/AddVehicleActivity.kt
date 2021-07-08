package io.android.momobill.ui.vehicle.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import io.android.momobill.R
import io.android.momobill.data.params.vehicle.AddVehicleParams
import io.android.momobill.databinding.ActivityAddVehicleBinding
import io.android.momobill.util.delegate.viewBinding
import io.android.momobill.util.extension.disable
import io.android.momobill.util.extension.enable
import io.android.momobill.util.extension.gone
import io.android.momobill.util.extension.showAlertDialog
import io.android.momobill.util.extension.showApiError
import io.android.momobill.util.extension.visible
import io.android.momobill.vo.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddVehicleActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityAddVehicleBinding::inflate)
    private val vm by viewModel<AddVehicleViewModel>()

    private val carSelected = MutableLiveData(true)
    private val motorcycleSelected = MutableLiveData(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        observeRegisterResult()
        setupInputFieldListener()

        binding.btnAdd.setOnClickListener {
            validateInput()
        }
    }

    private fun observeRegisterResult() {
        vm.addVehicleResult.observe(this) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pgbAddVehicle.visible()
                    binding.btnAdd.disable()
                }
                is ViewState.Success -> {
                    showAlertDialog(
                        "",
                        getString(R.string.add_vehicle_successful),
                        getString(R.string.close)
                    ) { finish() }
                }
                is ViewState.Error -> {
                    binding.pgbAddVehicle.gone()
                    binding.btnAdd.enable()
                    showApiError(supportFragmentManager, it, false) {
                        vm.addVehicle(getAddVehicleParams())
                    }
                }
            }
        }
    }

    private fun setupInputFieldListener() {
        carSelected.observe(this) {
            binding.cardCarIcon.isActivated = it
            binding.imgCarIcon.isActivated = it
        }
        motorcycleSelected.observe(this) {
            binding.cardMotorcycleIcon.isActivated = it
            binding.imgMotorcycleIcon.isActivated = it
        }

        binding.cardCarIcon.setOnClickListener {
            if (carSelected.value == false) {
                carSelected.value = true
                motorcycleSelected.value = false
            }
        }
        binding.cardMotorcycleIcon.setOnClickListener {
            if (motorcycleSelected.value == false) {
                carSelected.value = false
                motorcycleSelected.value = true
            }
        }
    }

    private fun validateInput() {
        val name = binding.etName.text.toString().trim()
        if (name.isEmpty()) {
            binding.etName.error = getString(R.string.empty_username)
            return
        }
        vm.addVehicle(getAddVehicleParams())
    }

    private fun getAddVehicleParams(): AddVehicleParams {
        val name = binding.etName.text.toString().trim()
        return AddVehicleParams(name)
    }
}
