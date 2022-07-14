package com.example.animequotes.domain.viewparams

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
@Parcelize
data class Quote(
    val anime: String,
    val id: Int,
    val name: String,
    val quote: String
): Parcelable