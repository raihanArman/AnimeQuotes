package com.example.animequotes.util

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * @author Raihan Arman
 * @date 23/07/2022
 */
object IntentUtils {
    fun shareImage(context: Context?, uri: Uri){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/jpg"
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        context?.startActivity(Intent.createChooser(intent, "Share quote"))
    }
}