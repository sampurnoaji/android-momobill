package io.android.momobill.di

import io.android.momobill.BuildConfig
import io.android.momobill.data.service.AuthService
import io.android.momobill.data.util.MockNetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { MockNetworkInterceptor(context = get()) }
    single { provideOkHttpClient(mockNetworkInterceptor = get()) }
    single { provideRetrofit(okHttpClient = get()) }
    single { provideApiService(retrofit = get()) }
}

private fun provideOkHttpClient(mockNetworkInterceptor: MockNetworkInterceptor) = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(mockNetworkInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): AuthService =
    retrofit.create(AuthService::class.java)