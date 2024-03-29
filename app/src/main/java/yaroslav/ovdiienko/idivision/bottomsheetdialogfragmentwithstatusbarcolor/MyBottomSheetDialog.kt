package yaroslav.ovdiienko.idivision.bottomsheetdialogfragmentwithstatusbarcolor

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_my_view.*

class MyBottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var toolbar: Toolbar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (super.onCreateDialog(savedInstanceState) as BottomSheetDialog).apply {
            window?.setDimAmount(0f)
            setOnShowListener { setupBottomSheetDialog(this) }
            setOnKeyListener { _, _, event ->
                if (event.keyCode == KeyEvent.KEYCODE_BACK) {
                    changeBackgroundToolbarViewAlpha()
                    dismiss()
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun setupBottomSheetDialog(dialog: BottomSheetDialog) {
        val bottomSheet =
            dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout? ?: return
        BottomSheetBehavior.from(bottomSheet).apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true
            isHideable = true
            setBottomSheetCallback(getBottomSheetCallback())
        }
    }

    private fun getBottomSheetCallback(): BottomSheetBehavior.BottomSheetCallback? {
        return object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                changeBackgroundToolbarViewAlpha(slideOffset)
            }

            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        dismiss()
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_my_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findViews()
        setupViews()
    }

    private fun findViews() {
        toolbar = tb_my_view
    }

    private fun setupViews() {
        toolbar.setNavigationOnClickListener {
            changeBackgroundToolbarViewAlpha()
            dismiss()
        }
    }

    private fun changeBackgroundToolbarViewAlpha(slideOffset: Float = 0f) {
        (activity as MainActivity?)?.setToolbarViewAlpha(slideOffset)
    }

    companion object {
        fun getInstance(): MyBottomSheetDialog {
            return MyBottomSheetDialog()
        }
    }
}