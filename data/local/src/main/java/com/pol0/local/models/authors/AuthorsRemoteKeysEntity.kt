package com.samples.quotestestrun.data.local.models.authors

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors_remote_keys_table")
data class AuthorsRemoteKeysEntity(
    @PrimaryKey
    val authorId: String,
    val prevKey: Int?,
    val nextKey: Int?
)