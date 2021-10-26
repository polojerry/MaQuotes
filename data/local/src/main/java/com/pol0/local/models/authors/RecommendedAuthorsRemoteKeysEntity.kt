package com.pol0.local.models.authors

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recommended_authors_remote_keys_table")
data class RecommendedAuthorsRemoteKeysEntity(
    @PrimaryKey
    val authorId: String,
    val prevKey: Int?,
    val nextKey: Int?
)