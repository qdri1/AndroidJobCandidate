package app.storytel.candidate.com.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import app.storytel.candidate.com.R
import app.storytel.candidate.com.databinding.DialogRegularBinding

private const val TITLE = "TITLE"
private const val DESCRIPTION = "DESCRIPTION"
private const val NEGATIVE_BUTTON_TEXT = "NEGATIVE_BUTTON_TEXT"
private const val POSITIVE_BUTTON_TEXT = "POSITIVE_BUTTON_TEXT"

class RegularDialog : DialogFragment() {

    lateinit var binding: DialogRegularBinding
    private var onNegativeClick: (() -> Unit)? = null
    private var onPositiveClick: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), R.style.DialogTheme)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_rounded_background)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogRegularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getCharSequence(TITLE)
        val description = arguments?.getCharSequence(DESCRIPTION)
        val negativeButtonText = arguments?.getCharSequence(NEGATIVE_BUTTON_TEXT)
        val positiveButtonText = arguments?.getCharSequence(POSITIVE_BUTTON_TEXT)

        when (title) {
            null -> binding.tvTitle.visibility = View.GONE
            else -> binding.tvTitle.text = title
        }
        when (description) {
            null -> binding.tvDescription.visibility = View.GONE
            else -> binding.tvDescription.text = description
        }
        when {
            negativeButtonText == null || onNegativeClick == null -> binding.btnNegative.visibility = View.GONE
            else -> binding.btnNegative.text = negativeButtonText
        }
        when {
            positiveButtonText == null || onPositiveClick == null -> binding.btnPositive.visibility = View.GONE
            else -> binding.btnPositive.text = positiveButtonText
        }

        binding.btnNegative.setOnClickListener {
            onNegativeClick?.invoke()
            dismiss()
        }
        binding.btnPositive.setOnClickListener {
            onPositiveClick?.invoke()
            dismiss()
        }
    }

    //region Builder
    class Builder {
        private var title: CharSequence? = null
        private var description: CharSequence? = null
        private var negativeButtonText: CharSequence? = null
        private var positiveButtonText: CharSequence? = null
        private var onNegativeClick: (() -> Unit)? = null
        private var onPositiveClick: (() -> Unit)? = null

        fun setTitle(title: CharSequence): Builder {
            this.title = title
            return this
        }

        fun setDescription(description: CharSequence): Builder {
            this.description = description
            return this
        }

        fun setNegativeButton(negativeButtonText: CharSequence, onClick: () -> Unit): Builder {
            this.negativeButtonText = negativeButtonText
            this.onNegativeClick = onClick
            return this
        }

        fun setPositiveButton(positiveButtonText: CharSequence, onClick: () -> Unit): Builder {
            this.positiveButtonText = positiveButtonText
            this.onPositiveClick = onClick
            return this
        }

        fun build(): RegularDialog {
            val dialog = RegularDialog()
            val args = Bundle()
            args.putCharSequence(TITLE, title)
            args.putCharSequence(DESCRIPTION, description)
            args.putCharSequence(NEGATIVE_BUTTON_TEXT, negativeButtonText)
            args.putCharSequence(POSITIVE_BUTTON_TEXT, positiveButtonText)
            dialog.onNegativeClick = onNegativeClick
            dialog.onPositiveClick = onPositiveClick
            dialog.arguments = args
            return dialog
        }
    }
    //endregion

}