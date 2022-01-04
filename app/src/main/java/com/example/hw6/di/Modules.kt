package com.example.hw6.di

import com.example.hw6.BuildConfig
import com.example.hw6.MovieApplication
import com.example.hw6.client.ResourceProvider
import com.example.hw6.service.MovieInterceptor
import com.example.hw6.service.MovieService
import com.example.hw6.viewmodel.MovieRepository
import com.example.hw6.viewmodel.MovieViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { androidApplication() as MovieApplication }
    single { ResourceProvider(get()) }
}

const val OK_HTTP_TIMEOUT = 40L

val networkModule = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        MoshiConverterFactory.create(get())
    }
    single {
        OkHttpClient.Builder().apply {
            connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(MovieInterceptor())
        }
    }
}

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(get<MoshiConverterFactory>())
            .client(get<OkHttpClient.Builder>().build())
            .build()
            .create(MovieService::class.java)
    }
}

val repositoriesModule = module {
    single { MovieRepository(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
}