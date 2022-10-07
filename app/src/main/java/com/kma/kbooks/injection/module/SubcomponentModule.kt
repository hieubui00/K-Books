package com.kma.kbooks.injection.module

import com.kma.kbooks.injection.component.MainComponent
import dagger.Module

@Module(subcomponents = [MainComponent::class])
class SubcomponentModule
