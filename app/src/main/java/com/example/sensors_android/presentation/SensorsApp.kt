package com.example.sensors_android.presentation

import android.app.Application
import com.example.sensors_android.presentation.viewmodels.utils.validatorsModule
import com.example.sensors_android.presentation.viewmodels.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SensorsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SensorsApp)
            modules(viewModelsModule, validatorsModule)
        }
    }
}