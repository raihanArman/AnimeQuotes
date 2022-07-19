package com.example.animequotes.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.animequotes.BuildConfig
import com.example.animequotes.data.network.QuoteApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            val clientBuilder = OkHttpClient.Builder()

            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
//            clientBuilder.addInterceptor { chain ->
//                val newRequest = chain.request().newBuilder()
//                    .addHeader( //I can't get token because there is no context here.
//                        "Authorization", "Bearer ${PreferencesHelper.getInstance(context).token.toString()}"
//                    )
//                    .build()
//                chain.proceed(newRequest)
//            }



            clientBuilder.readTimeout(120, TimeUnit.SECONDS)
            clientBuilder.writeTimeout(120, TimeUnit.SECONDS)
            clientBuilder.build()
        }
        single {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
        }
        single {
            get<Retrofit>().create(QuoteApiService::class.java)
        }
    }
}