package com.example.animequotes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animequotes.data.local.dao.QuoteDao
import com.example.animequotes.data.local.entity.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = true)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quotesDao(): QuoteDao

    companion object {
        private const val DB_NAME = "quote_db"
        fun create(context: Context): QuoteDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                QuoteDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}