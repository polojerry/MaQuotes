package com.pol0.maquotes.model

data class AuthorPresentation(
    val id: String,
    val bio: String,
    val dateAdded: String,
    val dateModified: String,
    val description: String,
    val link: String,
    val name: String,
    val quoteCount: Int,
    val slug: String
)