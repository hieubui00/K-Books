package com.kma.kbooks

import android.app.Application
import com.kma.kbooks.injection.component.ApplicationComponent
import com.kma.kbooks.injection.component.DaggerApplicationComponent
import com.kma.kbooks.util.DebugTree
import timber.log.Timber

class KBooksApplication : Application() {
    lateinit var component: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
        component = DaggerApplicationComponent.factory().create(this)
    }
}
