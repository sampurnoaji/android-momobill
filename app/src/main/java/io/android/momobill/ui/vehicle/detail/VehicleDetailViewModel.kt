package io.android.momobill.ui.vehicle.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.momobill.domain.entity.vehicle.VehicleDetail
import io.android.momobill.domain.usecase.vehicle.GetVehicleDetailUseCase
import io.android.momobill.util.extension.onError
import io.android.momobill.util.extension.onSuccess
import io.android.momobill.vo.ViewState
import kotlinx.coroutines.launch

class VehicleDetailViewModel(private val getVehicleDetailUseCase: GetVehicleDetailUseCase) :
    ViewModel() {

    private val _vehicleDetail = MutableLiveData<ViewState<VehicleDetail>>()
    val vehicleDetail = liveData { emitSource(_vehicleDetail) }

    fun getVehicleDetail(vehicleId: Int) {
        _vehicleDetail.value = ViewState.Loading
        viewModelScope.launch {
            getVehicleDetailUseCase(vehicleId)
                .onSuccess { _vehicleDetail.value = ViewState.Success(it) }
                .onError { _vehicleDetail.value = ViewState.Error(it) }
        }
    }
}
