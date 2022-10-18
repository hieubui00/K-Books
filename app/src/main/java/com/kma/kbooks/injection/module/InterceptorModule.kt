package com.kma.kbooks.injection.module

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.kma.kbooks.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.internal.platform.Platform
import javax.inject.Named

@Module
class InterceptorModule {

    @Provides
    @Named("logging")
    fun provideLoggingInterceptor(): Interceptor = LoggingInterceptor.Builder()
        .loggable(BuildConfig.DEBUG)
        .setLevel(Level.BASIC)
        .log(Platform.INFO)
        .request("Request")
        .response("Response")
        .build()
}
