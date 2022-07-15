package com.example.animequotes.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.animequotes.BuildConfig
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
object NetworkModule {
    val network = module {
        single {
            ChuckerInterceptor.Builder(androidContext()).build()
        }
        single{
            OkHttpClient.Builder()
                .addInterceptor(get())
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }
        single {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
        }
    }
}