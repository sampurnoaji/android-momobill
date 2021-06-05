package io.android.momobill.di

import io.android.momobill.data.dispatcher.CoroutineDispatcherProvider
import io.android.momobill.data.dispatcher.DispatcherProvider
import io.android.momobill.data.source.UserRepositoryImpl
import io.android.momobill.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<DispatcherProvider> { CoroutineDispatcherProvider() }

    single<UserRepository> {
        UserRepositoryImpl(
            remoteDataSource = get(),
            dispatcher = get(),
            moviesResponseMapper = get()
        )
    }
}