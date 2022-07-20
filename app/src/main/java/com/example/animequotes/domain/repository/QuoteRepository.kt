package com.example.animequotes.domain.repository

import com.example.animequotes.base.wrapper.DataResource
import com.example.animequotes.data.local.entity.QuoteEntity
import com.example.animequotes.data.network.model.QuoteResponse
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
interface QuoteRepository {
    suspend fun getRandomQuote(): Flow<DataResource<QuoteResponse>>
    suspend fun getFavorite(): Flow<DataResource<List<QuoteEntity>>>
    suspend fun addFavorite(entity: QuoteEntity): Flow<DataResource<Long>>
    suspend fun getFavoriteQuotesById(id: String?): Flow<DataResource<QuoteEntity?>>
    suspend fun deleteFavoriteQuote(entity: QuoteEntity): Flow<DataResource<Int>>
}