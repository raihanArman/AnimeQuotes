package com.example.animequotes.data.repository

import com.example.animequotes.base.arch.BaseRepositoryImpl
import com.example.animequotes.base.wrapper.DataResource
import com.example.animequotes.data.network.datasource.QuoteNetworkDataSource
import com.example.animequotes.data.network.model.QuoteResponse
import com.example.animequotes.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
class QuoteRepositoryImpl(
    private val networkDataSource: QuoteNetworkDataSource
): QuoteRepository, BaseRepositoryImpl() {
    override suspend fun getRandomQuote(): Flow<DataResource<QuoteResponse>> = flow {
        emit(safeNetwrokCall { networkDataSource.getRandomQuote() })
    }
}

