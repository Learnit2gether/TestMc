package com.example.testmc

import android.app.Application
import com.example.testmc.di.pagingModule
import com.example.testmc.di.repositoryModule
import com.example.testmc.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class AppEx : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppEx)
            modules(listOf(viewModelModule, repositoryModule))
        }
    }

}