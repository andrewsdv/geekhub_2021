package com.example.hw6.helper

import android.graphics.Color
import java.lang.Exception

class ProgressBarHelper {
    companion object {
        fun getProgressDrawableColor(rate: Double) : Int {
            when (rate) {
                in 7.0..10.0 -> {
                    return Color.GREEN
                }
                in 4.0..7.0 -> {
                    return Color.YELLOW
                }
                in 0.0..4.0 -> {
                    return Color.RED
                }
            }

            throw Exception()
        }
    }
}