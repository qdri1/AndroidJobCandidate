package app.storytel.candidate.com.mvvm.abstract

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import app.storytel.candidate.com.R
import app.storytel.candidate.com.dialog.RegularDialog

abstract class AbstractFragment<VB : ViewBinding> : Fragment() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPrepared()
    }

    abstract fun viewPrepared()

    protected fun showErrorDialog(onPositiveClick: () -> Unit) {
        RegularDialog.Builder()
                .setTitle(getString(R.string.error_dialog_title))
                .setDescription(getString(R.string.error_dialog_message))
                .setPositiveButton(getString(R.string.error_dialog_positive_button), onPositiveClick)
                .build()
                .show(childFragmentManager, "errorDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
