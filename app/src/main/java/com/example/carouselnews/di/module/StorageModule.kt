package com.example.carouselnews.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
class StorageModule(private val application: Application) {

    companion object {
        const val IO_DISPATCHER = "IO_DISPATCHER"
        const val MAIN_DISPATCHER = "MAIN_DISPATCHER"
    }

    @Named(IO_DISPATCHER)
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Named(MAIN_DISPATCHER)
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }
}
