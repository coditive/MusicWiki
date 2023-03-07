package com.example.musicwiki.di

import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideApiRequest(
        okHttpClient: OkHttpClient
    ): ApiService = Retrofit.Builder()
        .baseUrl("http://ws.audioscrobbler.com/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
        .create(ApiService::class.java)

}


