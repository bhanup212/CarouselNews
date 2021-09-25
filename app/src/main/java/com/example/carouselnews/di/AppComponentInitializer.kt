package com.example.carouselnews.di

import android.app.Application
import com.example.carouselnews.di.module.StorageModule

object AppComponentInitializer {
    private lateinit var appComponent: AppComponent
    private lateinit var application: Application

    fun setApplication(application: Application){
        AppComponentInitializer.application = application
    }

    fun getComponent(): AppComponent {
        if (!AppComponentInitializer::appComponent.isInitialized){
            appComponent = DaggerAppComponent.builder().storageModule(StorageModule(application)).build()
        }
        return appComponent
    }
}