package io.android.momobill.util.extension

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog

inline fun <reified T : Activity> Activity.start() {
    startActivity(Intent(this, T::class.java))
}

fun Activity.hideSystemUI() {
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
            // Set the content to appear under the system bars so that the
            // content doesn't resize when the system bars hide and show.
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // Hide the nav bar and status bar
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN)
}

fun Activity.showSystemUI() {
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
}

fun Activity.showAlertDialog(
    title: String,
    message: String,
    positiveButtonText: String,
    onPositiveButtonPressed: () -> Unit = {}
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
            onPositiveButtonPressed()
        }
        .show()
}

fun Activity.showAlertDialog(
    title: String,
    message: String,
    positiveButtonText: String,
    onPositiveButtonPressed: () -> Unit = {},
    negativeButtonText: String,
    onNegativeButtonPressed: () -> Unit = {}
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
            onPositiveButtonPressed()
        }
        .setNegativeButton(negativeButtonText) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
            onNegativeButtonPressed()
        }
        .show()
}