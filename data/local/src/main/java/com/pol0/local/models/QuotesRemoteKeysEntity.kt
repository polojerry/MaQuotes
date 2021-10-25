package com.pol0.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes_remote_keys_table")
data class QuotesRemoteKeysEntity(
    @PrimaryKey
    val quoteId: String,
    val prevKey: Int?,
    val nextKey: Int?
)