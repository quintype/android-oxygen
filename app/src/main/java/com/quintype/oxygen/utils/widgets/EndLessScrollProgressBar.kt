package com.quintype.oxygen.utils.widgets

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.vikatanapp.R
import com.vikatanapp.vikatan.utils.widgets.EndLessScrollProgressBar
import com.wang.avi.AVLoadingIndicatorView

class EndLessScrollProgressBar : LinearLayout {
    val tag: String = EndLessScrollProgressBar::class.java.simpleName

    constructor(context: Context?) : super(context) {
        context?.let { setupView(it) }
    }

    /*If we are specifying our custom view in the xml directly the below constructor with AttributeSet will be triggered.*/
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        context?.let { setupView(it) }
    }

    private lateinit var indicatorView: AVLoadingIndicatorView
    private lateinit var indicatorViewParentLayout: LinearLayout

    private fun setupView(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.endless_scroll_progress_bar_layout, this)
        indicatorView = view.findViewById(R.id.els_pb_indicator_view)
        indicatorViewParentLayout = view.findViewById(R.id.els_pb_indicator_layout)
    }

    fun showIndicator() {
        Log.d(tag, "Showing Indicator")
        indicatorViewParentLayout.visibility = View.VISIBLE
        indicatorView.smoothToShow()

    }

    fun hideIndicator() {
        Log.d(tag, "Hiding Indicator")
        indicatorView.smoothToHide()
        indicatorViewParentLayout.visibility = View.GONE
    }

}