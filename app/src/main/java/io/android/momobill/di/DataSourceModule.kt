package io.android.momobill.di

import io.android.momobill.data.source.auth.AuthLocalDataSource
import io.android.momobill.data.source.auth.AuthRemoteDataSource
import io.android.momobill.data.source.vehicle.VehicleRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { AuthRemoteDataSource(service = get()) }
    single { AuthLocalDataSource(sharedPreferences = get()) }
    single { VehicleRemoteDataSource(service = get()) }
}
