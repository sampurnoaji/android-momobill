package io.android.momobill.ui.home

import androidx.lifecycle.ViewModel
import io.android.momobill.domain.entity.vehicle.Vehicle

class HomeViewModel : ViewModel() {

    fun populateDummyVehicles(): List<Vehicle> {
        val vehicles = mutableListOf<Vehicle>()
        for (i in 1..10) {
            vehicles.add(Vehicle("BMW F44 2 Series Gran Coupe 220i xDrive"))
        }
        return vehicles
    }
}