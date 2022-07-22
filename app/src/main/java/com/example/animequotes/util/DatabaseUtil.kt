package com.example.animequotes.util

import android.content.Context
import java.io.File
import kotlin.jvm.Throws

/**
 * @author Raihan Arman
 * @date 20/07/2022
 */
object DatabaseUtil {
    @Throws
    fun getRoomDatabaseSize(context: Context, dbName: String): Long{
        val dbFolderPath = context.filesDir.absolutePath.replace("files", "databases")
        val dbFile = File("$dbFolderPath/$dbName")

        if(!dbFile.exists()) throw Exception("${dbFile.absolutePath} doesn't exist")

        return dbFile.length() / 1024 / 1024
    }
}