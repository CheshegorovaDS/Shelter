package com.example.shelter.app

import android.app.Application
import com.example.shelter.app.di.AppComponent
import com.example.shelter.app.di.DaggerAppComponent

class ShelterManagerApp: Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(applicationContext)
            .build()
    }

    fun getAppComponent() = appComponent
}