package com.plusapplc.minhaviagem

import android.app.Application
import com.plusapplc.minhaviagem.di.modules.appModule
import com.plusapplc.minhaviagem.di.modules.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MinhasViagensApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MinhasViagensApplication)
            modules(appModule, databaseModule)
        }
    }
}