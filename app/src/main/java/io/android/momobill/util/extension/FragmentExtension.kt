package io.android.momobill.util.extension

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment

inline fun <reified T : Activity> Fragment.start() {
    val intent = Intent(requireActivity(), T::class.java)
    startActivity(intent)
}