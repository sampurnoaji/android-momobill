package io.android.momobill.data.mapper.vehicle

import io.android.momobill.abstraction.Mapper
import io.android.momobill.data.dto.vehicle.VehicleDetailResponse
import io.android.momobill.domain.entity.vehicle.VehicleDetail
import io.android.momobill.util.extension.orZero

class VehicleDetailResponseMapper : Mapper<VehicleDetailResponse, VehicleDetail> {

    override fun invoke(dto: VehicleDetailResponse): VehicleDetail {
        return VehicleDetail(
            id = dto.id.orZero(),
            name = dto.name.orEmpty(),
            brand = dto.brand.orEmpty(),
            year = dto.year.orEmpty(),
            color = dto.color.orEmpty()
        )
    }
}
