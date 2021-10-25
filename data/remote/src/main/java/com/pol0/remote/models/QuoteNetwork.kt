package com.pol0.remote.models

import com.google.gson.annotations.SerializedName

data class QuoteNetwork(

    @SerializedName("_id")
    val id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)