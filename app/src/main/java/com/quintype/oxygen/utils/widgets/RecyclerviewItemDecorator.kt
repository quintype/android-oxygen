package com.quintype.oxygen.utils.widgets

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class RecyclerItemDecorator() : RecyclerView.ItemDecoration() {
    private var isStoryHasHeroImage: Boolean = false
    private var isHorizontalScrollView: Boolean = false
    internal var paddingLeft: Int = 0
    internal var paddingTop: Int = 0
    internal var paddingRight: Int = 0
    internal var paddingBottom: Int = 0

    constructor(isStoryHasHeroImage: Boolean, isHorizontalScrollView: Boolean, paddingLeft: Int, paddingTop: Int, paddingRight: Int, paddingBottom: Int) : this() {
        this.isStoryHasHeroImage = isStoryHasHeroImage
        this.isHorizontalScrollView = isHorizontalScrollView
        this.paddingLeft = paddingLeft
        this.paddingTop = paddingTop
        this.paddingRight = paddingRight
        this.paddingBottom = paddingBottom
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (!isHorizontalScrollView)
            if (isStoryHasHeroImage) {
                if (parent.getChildLayoutPosition(view) != 0) {
                    if (parent.getChildLayoutPosition(view) == 1) {
                        outRect.top = paddingTop
                    }
                    outRect.left = paddingLeft
                    outRect.right = paddingRight
                    outRect.bottom = paddingBottom
                }
            } else {
                if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
                    outRect.top = paddingTop
                }
                outRect.left = paddingLeft
                outRect.right = paddingRight
                outRect.bottom = paddingBottom
            }
        else {
            if (parent.getChildLayoutPosition(view) == 0)
                outRect.left = paddingLeft

            outRect.top = paddingTop
            outRect.right = paddingRight
            outRect.bottom = paddingBottom
        }
    }
}