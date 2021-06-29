package io.android.momobill.ui.vehicle.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.android.momobill.R
import io.android.momobill.databinding.ActivityVehicleDetailBinding
import io.android.momobill.domain.entity.vehicle.VehicleDetail
import io.android.momobill.util.delegate.viewBinding
import io.android.momobill.util.extension.gone
import io.android.momobill.util.extension.orZero
import io.android.momobill.util.extension.showApiError
import io.android.momobill.util.extension.visible
import io.android.momobill.vo.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class VehicleDetailActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityVehicleDetailBinding::inflate)
    private val vm by viewModel<VehicleDetailViewModel>()

    private var vehicleId = 0

    companion object {
        private const val INTENT_KEY_VEHICLE_ID = "vehicle_id"
        @JvmStatic
        fun start(context: Context, vehicleId: Int) {
            val starter = Intent(context, VehicleDetailActivity::class.java)
                .putExtra(INTENT_KEY_VEHICLE_ID, vehicleId)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        vehicleId = intent.extras?.getInt(INTENT_KEY_VEHICLE_ID).orZero()

        observeVehicleDetailResult()
        vm.getVehicleDetail(vehicleId)
    }

    private fun observeVehicleDetailResult() {
        vm.vehicleDetail.observe(this) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pgbVehicleDetail.visible()
                }
                is ViewState.Success -> {
                    binding.pgbVehicleDetail.gone()
                    showVehicleDetail(it.data)
                }
                is ViewState.Error -> {
                    binding.pgbVehicleDetail.gone()
                    showApiError(supportFragmentManager, it, false) {
                        vm.getVehicleDetail(vehicleId)
                    }
                }
            }
        }
    }

    private fun showVehicleDetail(vehicleDetail: VehicleDetail) {
        binding.imgVehicle.setImageResource(R.color.grey_300)
        binding.cardIcon.setCardBackgroundColor(ContextCompat.getColor(this, R.color.amber_900))
        binding.imgIcon.setImageResource(R.drawable.ic_steering_wheel_svgrepo_com)

        binding.tvBrand.text = vehicleDetail.brand
        binding.tvName.text = vehicleDetail.name
        binding.tvYear.text = vehicleDetail.year
        binding.tvColor.text = vehicleDetail.color
    }
}
