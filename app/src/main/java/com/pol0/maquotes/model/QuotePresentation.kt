package com.pol0.maquotes.model

data class QuotePresentation(
    val id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>,
    val isFavourite: Boolean = false
)