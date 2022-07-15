package com.example.animequotes

import android.app.Application
import com.example.animequotes.di.InjectionModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(InjectionModules.getModules())
        }
    }
}