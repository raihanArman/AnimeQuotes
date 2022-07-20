package com.example.animequotes.data.local.datasource

import com.example.animequotes.data.local.dao.QuoteDao
import com.example.animequotes.data.local.entity.QuoteEntity

/**
 * @author Raihan Arman
 * @date 20/07/2022
 */
class QuoteLocalDataSourceImpl(
    private val quotesDao: QuoteDao
): QuoteLocalDataSource {
    override suspend fun getFavoriteQuotes(): List<QuoteEntity> = quotesDao.getFavoriteQuotes()

    override suspend fun getFavoriteQuotesById(id: String?): QuoteEntity? = quotesDao.getFavoriteQuotesByID(id)

    override suspend fun addFavorite(quoteEntity: QuoteEntity): Long = quotesDao.addFavoriteQuote(quoteEntity)

    override suspend fun deleteFavorite(quoteEntity: QuoteEntity): Int = quotesDao.removeFavoriteQuote(quoteEntity)
}

interface QuoteLocalDataSource {
    suspend fun getFavoriteQuotes(): List<QuoteEntity>
    suspend fun getFavoriteQuotesById(id : String?): QuoteEntity?
    suspend fun addFavorite(quoteEntity: QuoteEntity): Long
    suspend fun deleteFavorite(quoteEntity: QuoteEntity): Int
}