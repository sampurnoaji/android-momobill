package io.android.momobill.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.android.momobill.R
import io.android.momobill.data.util.NoInternetConnection
import io.android.momobill.data.util.ServerErrorException
import io.android.momobill.data.util.TimeoutException
import io.android.momobill.databinding.FragmentAlertBottomSheetDialogBinding
import io.android.momobill.vo.ViewState

class AlertBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAlertBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    private var retryAction: () -> Unit = { }
    private var error: ViewState.Error? = null
    private var closeScreenOnCancel: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_MaterialComponents_BottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.setCanceledOnTouchOutside(false)
        _binding = FragmentAlertBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvErrorMessage.text = getString(
            when (this.error?.cause) {
                is NoInternetConnection, is TimeoutException -> R.string.internet_connection_error
                is ServerErrorException -> R.string.server_error
                else -> R.string.unknown_error
            }
        )

        binding.btnRetry.setOnClickListener {
            retryAction()
            dismiss()
        }
    }

    override fun dismiss() {
        super.dismiss()
        error = null
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        error = null

        if (closeScreenOnCancel) {
            if (parentFragmentManager.backStackEntryCount == 0) {
                activity?.onBackPressed()
            } else {
                parentFragmentManager.popBackStack()
            }
        }
    }

    fun setError(
        error: ViewState.Error,
        closeScreenOnCancel: Boolean,
        retryAction: () -> Unit,
        fragmentManager: FragmentManager
    ) {
        this.error = error
        this.closeScreenOnCancel = closeScreenOnCancel
        this.retryAction = retryAction

        show(fragmentManager, javaClass.simpleName)
        fragmentManager.executePendingTransactions()
    }

    companion object {
        private lateinit var INSTANCE: AlertBottomSheetDialogFragment

        fun getInstance(): AlertBottomSheetDialogFragment {
            synchronized(AlertBottomSheetDialogFragment::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = AlertBottomSheetDialogFragment()
                }
            }
            return INSTANCE
        }
    }
}