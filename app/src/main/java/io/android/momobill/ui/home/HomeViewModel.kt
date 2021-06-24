package io.android.momobill.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.momobill.domain.entity.vehicle.Vehicle
import io.android.momobill.domain.usecase.vehicle.GetVehiclesUseCase
import io.android.momobill.util.extension.onError
import io.android.momobill.util.extension.onSuccess
import io.android.momobill.vo.ViewState
import kotlinx.coroutines.launch

class HomeViewModel(private val getVehiclesUseCase: GetVehiclesUseCase) : ViewModel() {

    private val _vehicles = MutableLiveData<ViewState<List<Vehicle>>>()
    val vehicles = liveData { emitSource(_vehicles) }

    fun getVehicles() {
        _vehicles.value = ViewState.Loading
        viewModelScope.launch {
            getVehiclesUseCase()
                .onSuccess { _vehicles.value = ViewState.Success(it) }
                .onError { _vehicles.value = ViewState.Error(it) }
        }
    }
}
