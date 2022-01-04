package com.example.hw6

import android.app.Application
import com.example.hw6.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(koinModules)
        }
    }

}