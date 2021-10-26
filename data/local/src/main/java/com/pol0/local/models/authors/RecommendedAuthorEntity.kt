package com.pol0.local.models.authors

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recommended_authors_table")
data class RecommendedAuthorEntity(
    @PrimaryKey
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
