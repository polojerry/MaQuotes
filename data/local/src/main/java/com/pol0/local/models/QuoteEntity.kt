package com.pol0.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes_table")
data class QuoteEntity(
    @PrimaryKey
    val id: String,

    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>,
    val isFavourite :Boolean = false
)