package com.kma.kbooks.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.kma.kbooks.KBooksApplication
import com.kma.kbooks.R
import com.kma.kbooks.injection.component.MainComponent

class MainActivity : AppCompatActivity() {
    lateinit var component: MainComponent
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        injectComponents()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_main)
    }

    private fun injectComponents() {
        component = (application as KBooksApplication).component
            .mainComponent()
            .create(this)
            .also { it.inject(this) }
    }
}
