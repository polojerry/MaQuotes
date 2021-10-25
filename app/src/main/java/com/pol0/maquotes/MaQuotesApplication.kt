package com.pol0.maquotes

import android.app.Application
import timber.log.Timber

class MaQuotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}