package io.android.momobill.di

import io.android.momobill.data.source.auth.AuthRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { AuthRemoteDataSource(service = get()) }
}