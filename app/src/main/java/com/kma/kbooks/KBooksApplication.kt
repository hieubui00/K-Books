package com.kma.kbooks

import android.app.Application
import com.kma.kbooks.util.DebugTree
import timber.log.Timber

class KBooksApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}
