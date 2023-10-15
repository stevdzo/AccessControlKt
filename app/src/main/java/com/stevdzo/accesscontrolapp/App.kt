package com.stevdzo.accesscontrolapp

import com.stevdzo.accesscontrolapp.di.AppModuleImpl
import android.app.Application
import com.stevdzo.accesscontrolapp.di.AppModule

class App: Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}