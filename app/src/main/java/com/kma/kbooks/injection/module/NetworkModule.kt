package com.kma.kbooks.injection.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kma.kbooks.BuildConfig
import com.kma.kbooks.data.remote.request.KBooksService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [InterceptorModule::class])
class NetworkModule {

    @Provides
    fun provideOkHttpClient(
        @Named("logging") loggingInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .baseUrl(BuildConfig.API_HOST)
        .build()

    @Singleton
    @Provides
    fun provideKBooksService(retrofit: Retrofit): KBooksService {
        return retrofit.create(KBooksService::class.java)
    }
}