package io.android.momobill.di

import io.android.momobill.data.source.UserRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { UserRemoteDataSource(service = get()) }
}