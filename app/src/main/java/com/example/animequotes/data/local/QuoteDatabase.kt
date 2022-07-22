package com.example.animequotes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animequotes.data.local.dao.QuoteDao
import com.example.animequotes.data.local.entity.QuoteEntity
import com.example.animequotes.util.Constant.DB_NAME

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = true)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quotesDao(): QuoteDao

    companion object {
        fun create(context: Context): QuoteDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                QuoteDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}