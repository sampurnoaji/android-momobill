package io.android.momobill.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.android.momobill.R
import io.android.momobill.databinding.FragmentHomeBinding
import io.android.momobill.domain.entity.vehicle.Vehicle
import io.android.momobill.ui.vehicle.detail.VehicleDetailActivity
import io.android.momobill.util.delegate.viewBinding
import io.android.momobill.util.extension.start
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val vm by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupContentDashboard()
        setupVehiclesRecyclerView()
    }

    private fun setupContentDashboard() {
        with(binding.contentCar) {
            cardIcon.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.amber_900))
            imgIcon.setImageResource(R.drawable.ic_steering_wheel_svgrepo_com)
            tvTitle.text = getString(R.string.car)
            tvSubtitle.text = "15"
        }

        with(binding.contentMotorcycle) {
            cardIcon.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_green_a700))
            imgIcon.setImageResource(R.drawable.ic_baseline_sports_motorsports_24)
            tvTitle.text = getString(R.string.motorcycle)
            tvSubtitle.text = "212"
        }
    }

    private fun setupVehiclesRecyclerView() {
        val vehicles = vm.populateDummyVehicles()
        val callback = object : VehicleListAdapter.VehicleListCallback {
            override fun onVehicleClicked(vehicle: Vehicle) {
                start<VehicleDetailActivity>()
            }
        }
        val vehicleListAdapter = VehicleListAdapter(vehicles, callback)
        with(binding.rvVehicles) {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = vehicleListAdapter
            isFocusable = false
        }
    }
}
