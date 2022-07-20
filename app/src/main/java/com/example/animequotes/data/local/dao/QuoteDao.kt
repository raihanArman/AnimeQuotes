package com.example.animequotes.data.local.dao

import androidx.room.*
import com.example.animequotes.data.local.entity.QuoteEntity

/**
 * @author Raihan Arman
 * @date 20/07/2022
 */
@Dao
interface QuoteDao {
    @Query("SELECT * FROM quotes")
    suspend fun getFavoriteQuotes(): List<QuoteEntity>

    @Query("SELECT * FROM quotes WHERE id == :id LIMIT 1")
    suspend fun getFavoriteQuotesByID(id : String?): QuoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteQuote(note: QuoteEntity): Long

    @Delete
    suspend fun removeFavoriteQuote(note: QuoteEntity): Int
}