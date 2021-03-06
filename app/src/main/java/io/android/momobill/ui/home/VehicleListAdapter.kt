package io.android.momobill.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.android.momobill.R
import io.android.momobill.databinding.ItemListVehicleBinding
import io.android.momobill.domain.entity.vehicle.Vehicle

class VehicleListAdapter(
    private var vehicles: List<Vehicle>,
    private val callback: VehicleListCallback
) : RecyclerView.Adapter<VehicleListAdapter.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(vehicles[position], callback)
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

    fun refreshData(vehicles: List<Vehicle>) {
        this.vehicles = vehicles
        notifyDataSetChanged()
    }

    class ContentViewHolder(private val binding: ItemListVehicleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(viewGroup: ViewGroup): ContentViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val binding = ItemListVehicleBinding.inflate(inflater, viewGroup, false)
                return ContentViewHolder(binding)
            }
        }

        fun bind(vehicle: Vehicle, callback: VehicleListCallback) {
            binding.tvCar.text = vehicle.name
            binding.imgCar.load(vehicle.imageUrl)
            binding.imgCarLogo.setImageResource(R.drawable.ic_bmw_logo)

            binding.container.setOnClickListener { callback.onVehicleClicked(vehicle) }
        }
    }

    interface VehicleListCallback {
        fun onVehicleClicked(vehicle: Vehicle)
    }
}
