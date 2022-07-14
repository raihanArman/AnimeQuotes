package com.example.animequotes.data.network.model


import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("anime")
    val anime: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("quote")
    val quote: String?
)