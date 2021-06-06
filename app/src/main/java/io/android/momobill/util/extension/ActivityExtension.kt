package io.android.momobill.util.extension

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog

inline fun <reified T : Activity> Activity.start() {
    startActivity(Intent(this, T::class.java))
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
        .setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface, i: Int ->
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
        .setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
            onPositiveButtonPressed()
        }
        .setNegativeButton(negativeButtonText) { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
            onNegativeButtonPressed()
        }
        .show()
}