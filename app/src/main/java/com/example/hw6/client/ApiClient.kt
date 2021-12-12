package com.example.hw6.client

import com.example.hw6.BuildConfig
import com.example.hw6.service.MovieInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private val moshiBuilder = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val moshiConverterFactory = MoshiConverterFactory.create(moshiBuilder)

    private val interceptor = MovieInterceptor()

    private val client = OkHttpClient.Builder().apply {
        connectTimeout(40, TimeUnit.SECONDS)
        writeTimeout(40, TimeUnit.SECONDS)
        readTimeout(40, TimeUnit.SECONDS)
        addInterceptor(interceptor)
    }.build()

    val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(moshiConverterFactory).client(client).build()
}