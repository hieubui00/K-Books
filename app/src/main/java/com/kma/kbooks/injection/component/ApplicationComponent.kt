package com.kma.kbooks.injection.component

import android.app.Application
import com.kma.kbooks.injection.module.RepositoryModule
import com.kma.kbooks.injection.module.SubcomponentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        SubcomponentModule::class
    ]
)
interface ApplicationComponent {

    fun mainComponent(): MainComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
