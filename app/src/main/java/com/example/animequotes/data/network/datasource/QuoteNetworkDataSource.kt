package com.example.animequotes.data.network.datasource

import com.example.animequotes.data.network.QuoteApiService
import com.example.animequotes.data.network.model.QuoteResponse

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
class QuoteNetworkDataSourceImpl(
    private val apiService: QuoteApiService
): QuoteNetworkDataSource {
    override suspend fun getRandomQuote(): QuoteResponse {
        return apiService.getRandomQuotes()
    }
}

interface QuoteNetworkDataSource{
    suspend fun getRandomQuote():QuoteResponse
}