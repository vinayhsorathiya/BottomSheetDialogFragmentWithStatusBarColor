package yaroslav.ovdiienko.idivision.bottomsheetdialogfragmentwithstatusbarcolor

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var clickableTextView: TextView
    private lateinit var backgroundToolBarView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        setupViews()
    }

    private fun findViews() {
        clickableTextView = hello_world
        backgroundToolBarView = v_background_toolbar
    }

    private fun setupViews() {
        clickableTextView.setOnClickListener {
            val dialog = MyBottomSheetDialog.getInstance()
            dialog.show(supportFragmentManager, MyBottomSheetDialog::class.java.simpleName)
        }
    }

    fun setToolbarViewAlpha(alpha: Float) {
        backgroundToolBarView.alpha = alpha
    }
}
