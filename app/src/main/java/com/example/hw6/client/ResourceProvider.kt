package com.example.hw6.client

import android.content.Context

class ResourceProvider(private val context: Context) {
    fun getString(resId: Int) = context.getString(resId)
}