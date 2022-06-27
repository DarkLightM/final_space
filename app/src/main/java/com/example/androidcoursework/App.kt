package com.example.androidcoursework

import android.app.Application
import com.example.androidcoursework.di.AppComponent
import com.example.androidcoursework.di.DaggerAppComponent
import com.example.androidcoursework.di.RoomModule

class App: Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().roomModule(RoomModule(this)).build()
    }
}