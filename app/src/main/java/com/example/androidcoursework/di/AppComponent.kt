package com.example.androidcoursework.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.Component

@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    fun inject(fragment: Fragment)
    fun inject(vm: MainViewModel)
    fun inject(activity: AppCompatActivity)
}