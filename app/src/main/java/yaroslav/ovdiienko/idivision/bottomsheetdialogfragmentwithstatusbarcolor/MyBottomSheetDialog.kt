package yaroslav.ovdiienko.idivision.bottomsheetdialogfragmentwithstatusbarcolor

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
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
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { setupBottomSheetDialog(dialog) }
        dialog.window?.setDimAmount(0f)
        return dialog
    }

    private fun setupBottomSheetDialog(dialog: BottomSheetDialog) {
        val bottomSheet =
            dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout? ?: return
        val behavior = BottomSheetBehavior.from(bottomSheet)
        setupDialogBehavior(behavior)
    }

    private fun setupDialogBehavior(behavior: BottomSheetBehavior<FrameLayout>?) {
        behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        behavior?.skipCollapsed = true
        behavior?.isHideable = true
        behavior?.setBottomSheetCallback(getBottomSheetCallback())
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
        (activity as MainActivity).setToolbarViewAlpha(slideOffset)
    }

    companion object {
        fun getInstance(): MyBottomSheetDialog {
            return MyBottomSheetDialog()
        }
    }
}