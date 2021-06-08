package io.android.momobill.di

import io.android.momobill.data.mapper.LoginResponseMapper
import io.android.momobill.data.mapper.UserInfoResponseMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { LoginResponseMapper() }
    factory { UserInfoResponseMapper() }
}