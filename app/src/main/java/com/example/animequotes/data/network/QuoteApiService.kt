package com.example.animequotes.data.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.animequotes.BuildConfig
import com.example.animequotes.data.network.model.QuoteResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
interface QuoteApiService {
    @GET("quotes")
    suspend fun getRandomQuotes(): QuoteResponse
}