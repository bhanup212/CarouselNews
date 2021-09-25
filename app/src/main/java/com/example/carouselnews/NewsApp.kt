package com.example.carouselnews

import android.app.Application
import com.example.carouselnews.di.AppComponentInitializer

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppComponentInitializer.setApplication(this)
    }
}
