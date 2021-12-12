package com.example.hw6.helper

import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import androidx.core.graphics.drawable.DrawableCompat
import com.example.hw6.R

fun getProgressDrawable(rate: Int, unwrappedDrawable: LayerDrawable) {
    when (rate) {
        in 70..100 -> {
            DrawableCompat.setTint(unwrappedDrawable, Color.GREEN)
        }
        in 40..70 -> {
            DrawableCompat.setTint(unwrappedDrawable, Color.YELLOW)
        }
        in 0..40 -> {
            DrawableCompat.setTint(unwrappedDrawable, Color.RED)
        }
    }

    unwrappedDrawable.findDrawableByLayerId(R.id.progressBackground)?.let {
        DrawableCompat.setTint(it, Color.BLACK)
    }
}