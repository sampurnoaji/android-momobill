package io.android.momobill.ui.vehicle.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.momobill.data.params.vehicle.AddVehicleParams
import io.android.momobill.domain.usecase.vehicle.AddVehicleUseCase
import io.android.momobill.util.extension.onError
import io.android.momobill.util.extension.onSuccess
import io.android.momobill.vo.ViewState
import kotlinx.coroutines.launch

class AddVehicleViewModel(private val addVehicleUseCase: AddVehicleUseCase) : ViewModel() {

    private val _addVehicleResult = MutableLiveData<ViewState<Boolean>>()
    val addVehicleResult = liveData { emitSource(_addVehicleResult) }

    fun addVehicle(params: AddVehicleParams) {
        _addVehicleResult.value = ViewState.Loading
        viewModelScope.launch {
            addVehicleUseCase(params)
                .onSuccess { _addVehicleResult.value = ViewState.Success(it) }
                .onError { _addVehicleResult.value = ViewState.Error(it) }
        }
    }
}
