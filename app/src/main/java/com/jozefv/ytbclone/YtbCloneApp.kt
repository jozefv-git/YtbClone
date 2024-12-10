package com.jozefv.ytbclone

import android.app.Application
import com.jozefv.ytbclone.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class YtbCloneApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Let Koin know about our app context, so it can be used to create dependencies
            androidContext(this@YtbCloneApp)
            // List of modules we need
            modules(coreModule)
        }
    }

}