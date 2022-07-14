package com.example.animequotes.data.network

import com.example.animequotes.data.network.model.QuoteResponse
import retrofit2.http.GET

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
interface QuoteApiService {
    @GET("quotes")
    suspend fun getRandomQuotes(): QuoteResponse
}