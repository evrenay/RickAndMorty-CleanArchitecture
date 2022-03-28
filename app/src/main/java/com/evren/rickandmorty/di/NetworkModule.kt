package com.evren.rickandmorty.di

import android.content.Context
import com.evren.rickandmorty.BuildConfig
import com.evren.rickandmorty.data.api.CharacterService
import com.evren.rickandmorty.util.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CONNECT_TIMEOUT = 20L
    private const val READ_TIMEOUT = 60L
    private const val WRITE_TIMEOUT = 120L


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(NetworkConnectionInterceptor(context))
            .connectTimeout(CONNECT_TIMEOUT, SECONDS)
            .readTimeout(READ_TIMEOUT, SECONDS)
            .writeTimeout(WRITE_TIMEOUT, SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideCharacterService(retrofit: Retrofit): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }

}