package com.quintype.oxygen.utils.widgets

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class RecyclerviewGridItemDecorator(val padding: Int, private val spanCount: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

//        if (includeEdge) {
        outRect.left = padding - column * padding / spanCount // spacing - column * ((1f / spanCount) * spacing)
        outRect.right = (column + 1) * padding / spanCount // (column + 1) * ((1f / spanCount) * spacing)

        if (position < spanCount) { // top edge
            outRect.top = padding
        }
        outRect.bottom = padding // item bottom
//        } else {
//            outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
//            outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
//            if (position >= spanCount) {
//                outRect.top = spacing // item top
//            }
//        }
    }
}