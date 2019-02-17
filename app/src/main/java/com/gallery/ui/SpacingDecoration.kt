package com.gallery.ui

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class SpacingDecoration(private val spacing: Int) : RecyclerView
.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State) {

        val layoutManager = parent.layoutManager
        val position = parent.getChildAdapterPosition(view)
        val adapter = parent.adapter ?: return

        if (layoutManager is GridLayoutManager) {
            val spanCount = layoutManager.spanCount
            val rowsCount = adapter.itemCount / spanCount
            val row = position / spanCount
            val column = position % spanCount
            val halfSpacing = spacing / 2

            outRect.top = halfSpacing
            outRect.left = halfSpacing
            outRect.right = halfSpacing
            outRect.bottom = halfSpacing

            if (column == 0) outRect.left = spacing
            else if (column == spanCount - 1) outRect.right = spacing

            if (row == 0) outRect.top = spacing
            else if (row == rowsCount - 1) outRect.bottom = spacing

        } else {
            outRect.top = spacing
            outRect.left = spacing
            outRect.right = spacing

            if (position == adapter.itemCount - 1) {
                outRect.bottom = spacing
            }
        }
    }
}