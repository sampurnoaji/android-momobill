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
import io.android.momobill.util.extension.gone
import io.android.momobill.util.extension.showApiError
import io.android.momobill.util.extension.start
import io.android.momobill.util.extension.visible
import io.android.momobill.vo.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val vm by viewModel<HomeViewModel>()

    private val vehicleListAdapter by lazy { VehicleListAdapter(emptyList(), vehicleListCallback) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDashboardContent()
        setupVehiclesRecyclerView()

        observeVehiclesResult()
        vm.getVehicles()

        binding.swipeRefreshLayout.setOnRefreshListener {
            vm.getVehicles()

        }
    }

    private fun observeVehiclesResult() {
        vm.vehicles.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pgbVehicles.visible()
                }
                is ViewState.Success -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.pgbVehicles.gone()
                    vehicleListAdapter.refreshData(it.data)
                }
                is ViewState.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.pgbVehicles.gone()
                    showApiError(childFragmentManager, it, false) {
                        vm.getVehicles()
                    }
                }
            }
        }
    }

    private fun setupDashboardContent() {
        with(binding.contentCar) {
            cardIcon.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.amber_900
                )
            )
            imgIcon.setImageResource(R.drawable.ic_steering_wheel_svgrepo_com)
            tvTitle.text = getString(R.string.car)
            tvSubtitle.text = "15"
        }

        with(binding.contentMotorcycle) {
            cardIcon.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_green_a700
                )
            )
            imgIcon.setImageResource(R.drawable.ic_baseline_sports_motorsports_24)
            tvTitle.text = getString(R.string.motorcycle)
            tvSubtitle.text = "212"
        }
    }

    private fun setupVehiclesRecyclerView() {
        with(binding.rvVehicles) {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = vehicleListAdapter
            isFocusable = false
        }
    }

    private val vehicleListCallback = object : VehicleListAdapter.VehicleListCallback {
        override fun onVehicleClicked(vehicle: Vehicle) {
            start<VehicleDetailActivity>()
        }
    }
}
