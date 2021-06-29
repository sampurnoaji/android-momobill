package io.android.momobill.di

import io.android.momobill.data.mapper.auth.LoginResponseMapper
import io.android.momobill.data.mapper.auth.UserInfoResponseMapper
import io.android.momobill.data.mapper.vehicle.VehicleDetailResponseMapper
import io.android.momobill.data.mapper.vehicle.VehicleResponseMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { LoginResponseMapper() }
    factory { UserInfoResponseMapper() }

    factory { VehicleResponseMapper() }
    factory { VehicleDetailResponseMapper() }
}
