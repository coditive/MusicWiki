package com.example.musicwiki.di

import android.content.Context
import com.example.musicwiki.BuildConfig
import com.example.musicwiki.data.remote.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): ApiService = Retrofit.Builder()
        .baseUrl("http://ws.audioscrobbler.com/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
        .create(ApiService::class.java)

}


