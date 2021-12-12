package com.example.hw6.service

import okhttp3.Interceptor
import okhttp3.Response

class MovieInterceptor : Interceptor {
    private val apiKey = "4b5565bde59ab3b594114c4d7708b052"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalUrl = request.url
        val newUrl = originalUrl.newBuilder().addEncodedQueryParameter("api_key", apiKey).build()
        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}