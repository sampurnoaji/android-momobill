package io.android.momobill.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.android.momobill.R
import io.android.momobill.databinding.ItemListVehicleBinding
import io.android.momobill.domain.entity.vehicle.Vehicle

class VehicleListAdapter(private val vehicles: List<Vehicle>) :
    RecyclerView.Adapter<VehicleListAdapter.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(vehicles[position])
    }

    override fun getItemCount(): Int {
        return vehicles.size
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

        fun bind(vehicle: Vehicle) {
            binding.tvCar.text = vehicle.name
            binding.imgCar.setImageResource(R.color.secondaryColor)
            binding.imgCarLogo.setImageResource(R.drawable.ic_bmw_logo)
        }
    }
}