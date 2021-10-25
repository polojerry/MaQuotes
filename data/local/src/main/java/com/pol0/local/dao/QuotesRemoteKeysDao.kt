package com.pol0.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pol0.local.models.QuotesRemoteKeysEntity

@Dao
interface QuotesRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<QuotesRemoteKeysEntity>)

    @Query("SELECT * FROM QUOTES_REMOTE_KEYS_TABLE WHERE quoteId = :quoteId")
    suspend fun remoteKeysQuoteId(quoteId: String): QuotesRemoteKeysEntity?

    @Query("DELETE FROM QUOTES_REMOTE_KEYS_TABLE")
    suspend fun clearRemoteKeys()
}