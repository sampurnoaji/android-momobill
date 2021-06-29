package io.android.momobill.di

import io.android.momobill.data.source.auth.AuthRepositoryImpl
import io.android.momobill.data.source.vehicle.VehicleRepositoryImpl
import io.android.momobill.domain.repository.AuthRepository
import io.android.momobill.domain.repository.VehicleRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            loginResponseMapper = get(),
            userInfoResponseMapper = get()
        )
    }

    single<VehicleRepository> {
        VehicleRepositoryImpl(
            remoteDataSource = get(),
            vehicleResponseMapper = get(),
            vehicleDetailResponseMapper = get()
        )
    }
}
