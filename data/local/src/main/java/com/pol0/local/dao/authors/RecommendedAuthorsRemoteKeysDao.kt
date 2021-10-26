package com.pol0.local.dao.authors

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pol0.local.models.authors.AuthorsRemoteKeysEntity
import com.pol0.local.models.authors.RecommendedAuthorsRemoteKeysEntity

@Dao
interface RecommendedAuthorsRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RecommendedAuthorsRemoteKeysEntity>)

    @Query("SELECT * FROM RECOMMENDED_AUTHORS_REMOTE_KEYS_TABLE WHERE authorId = :authorId")
    suspend fun remoteKeysAuthorId(authorId: String): RecommendedAuthorsRemoteKeysEntity?

    @Query("DELETE FROM RECOMMENDED_AUTHORS_REMOTE_KEYS_TABLE")
    suspend fun clearRemoteKeys()
}