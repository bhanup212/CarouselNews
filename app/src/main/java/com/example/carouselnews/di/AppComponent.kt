package com.example.carouselnews.di

import com.example.carouselnews.MainActivity
import com.example.carouselnews.di.module.NetworkModule
import com.example.carouselnews.di.module.StorageModule
import com.example.carouselnews.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, StorageModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}