package io.android.momobill.utils

import android.app.Activity
import android.content.Intent

inline fun <reified T : Activity> Activity.start() {
    startActivity(Intent(this, T::class.java))
}
