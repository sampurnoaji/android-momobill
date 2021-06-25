package io.android.momobill.util.extension

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import io.android.momobill.ui.AlertBottomSheetDialogFragment
import io.android.momobill.vo.ViewState

inline fun <reified T : Activity> Fragment.start() {
    val intent = Intent(requireActivity(), T::class.java)
    startActivity(intent)
}

fun Fragment.showApiError(
    manager: FragmentManager,
    error: ViewState.Error,
    closeScreenOnCancel: Boolean,
    retryAction: () -> Unit = {}
) {
    val alert = AlertBottomSheetDialogFragment.getInstance()
    alert.setError(error, closeScreenOnCancel, retryAction, manager)
}
