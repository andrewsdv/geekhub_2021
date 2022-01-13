package com.example.hw6.di

import androidx.room.Room
import com.example.hw6.BuildConfig
import com.example.hw6.MovieApplication
import com.example.hw6.client.ResourceProvider
import com.example.hw6.database.MoviesDataBase
import com.example.hw6.repository.MovieRepositoryImpl
import com.example.hw6.repository.datasource.LocalMovieDataSourceImpl
import com.example.hw6.repository.datasource.RemoteMovieDataSourceImpl
import com.example.hw6.service.MovieInterceptor
import com.example.hw6.service.MovieService
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
    single { LocalMovieDataSourceImpl(get(), get()) }
    single { RemoteMovieDataSourceImpl(get()) }
    single { MovieRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
}

private const val DB_NAME = "Movies.db"

val dbModule = module {

    single {
        Room.databaseBuilder(get(), MoviesDataBase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { get<MoviesDataBase>().getMovieDetailsDao() }
    factory { get<MoviesDataBase>().getMoviePreviewsDao() }
}