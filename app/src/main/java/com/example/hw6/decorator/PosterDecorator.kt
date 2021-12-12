package com.example.hw6.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PosterDecorator(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        val layout: GridLayoutManager.LayoutParams = view.layoutParams as GridLayoutManager.LayoutParams

        if (layout.spanIndex % 2 == 0) {
            outRect.left = offset
            outRect.right = offset / 2
        } else {
            outRect.right = offset
            outRect.left = offset / 2
        }
    }
}