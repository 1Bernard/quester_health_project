package com.example.questerhealth

import android.app.Application
import com.example.questerhealth.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class QuesterHealthApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@QuesterHealthApp)
            modules(appModule)
        }
    }
}