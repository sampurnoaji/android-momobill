package io.android.momobill.di

import io.android.momobill.data.mapper.MoviesResponseMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { MoviesResponseMapper() }
}