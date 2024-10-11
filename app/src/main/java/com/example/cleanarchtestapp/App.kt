package com.example.cleanarchtestapp

import android.app.Application
import com.example.cleanarchtestapp.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoinModule()
    }

    private fun setupKoinModule() {
        startKoin {
            // currently a workaround for koin as not having Level.ERROR causes a runtime crash
            androidContext(this@App)
            modules(koinModule)
        }
    }
}