package io.android.momobill.di

import io.android.momobill.data.dispatcher.CoroutineDispatcherProvider
import io.android.momobill.data.dispatcher.DispatcherProvider
import io.android.momobill.data.source.auth.AuthRepositoryImpl
import io.android.momobill.data.source.vehicle.VehicleRepositoryImpl
import io.android.momobill.domain.repository.AuthRepository
import io.android.momobill.domain.repository.VehicleRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<DispatcherProvider> { CoroutineDispatcherProvider() }

    single<AuthRepository> {
        AuthRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            dispatcher = get(),
            loginResponseMapper = get(),
            userInfoResponseMapper = get()
        )
    }

    single<VehicleRepository> {
        VehicleRepositoryImpl(
            remoteDataSource = get(),
            vehicleResponseMapper = get()
        )
    }
}
