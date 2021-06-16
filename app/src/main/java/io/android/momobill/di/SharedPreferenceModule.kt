package io.android.momobill.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import io.android.momobill.util.SharedPreferenceConstant
import org.koin.dsl.module

val sharedPreferenceModule = module {
    single { provideSharedPreference(applicationContext = get()) }
}

fun provideSharedPreference(applicationContext: Context): SharedPreferences {
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    return EncryptedSharedPreferences.create(
        SharedPreferenceConstant.PREF_NAME,
        masterKeyAlias,
        applicationContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}
