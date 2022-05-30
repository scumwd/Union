package com.example.union.app

import android.app.Application
import com.example.union.di.AppComponent
import com.example.union.di.AppModule
import com.example.union.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}