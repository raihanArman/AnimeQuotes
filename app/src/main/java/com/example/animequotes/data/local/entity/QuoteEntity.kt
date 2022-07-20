package com.example.animequotes.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author Raihan Arman
 * @date 20/07/2022
 */

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey
    val id: Int?,
    @ColumnInfo
    val anime: String?,
    @ColumnInfo
    val name: String?,
    @ColumnInfo
    val quote: String?,
    @ColumnInfo
    val isFavorite: Boolean?
)