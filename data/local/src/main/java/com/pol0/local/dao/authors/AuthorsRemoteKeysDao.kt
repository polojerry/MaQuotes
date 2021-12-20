package com.pol0.local.dao.authors

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pol0.local.models.authors.AuthorsRemoteKeysEntity

@Dao
interface AuthorsRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<AuthorsRemoteKeysEntity>)

    @Query("SELECT * FROM AUTHORS_REMOTE_KEYS_TABLE WHERE authorId = :authorId")
    suspend fun remoteKeysAuthorId(authorId: String): AuthorsRemoteKeysEntity?

    @Query("DELETE FROM AUTHORS_REMOTE_KEYS_TABLE")
    suspend fun clearRemoteKeys()
}