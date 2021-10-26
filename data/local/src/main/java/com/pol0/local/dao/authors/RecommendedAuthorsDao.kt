package com.pol0.local.dao.authors

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pol0.local.models.authors.RecommendedAuthorEntity

@Dao
interface RecommendedAuthorsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecommendedAuthors(authors: List<RecommendedAuthorEntity>)

    @Query("SELECT * FROM RECOMMENDED_AUTHORS_TABLE")
    fun recommendedAuthors(): DataSource.Factory<Int, RecommendedAuthorEntity>

    @Query("DELETE FROM RECOMMENDED_AUTHORS_TABLE")
    fun clearRecommendedAuthors()


}