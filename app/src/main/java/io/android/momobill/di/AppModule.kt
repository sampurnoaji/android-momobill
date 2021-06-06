package io.android.momobill.di

import org.koin.core.context.loadKoinModules

val appModule = loadKoinModules(
    listOf(
        sharedPreferenceModule,
        networkModule,
        dataSourceModule,
        mapperModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )
)
