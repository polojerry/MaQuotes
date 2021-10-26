package com.pol0.local.dao.authors

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samples.quotestestrun.data.local.models.authors.AuthorEntity

@Dao
interface AuthorsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAuthors(authors: List<AuthorEntity>)

    @Query("SELECT * FROM AUTHORS_TABLE ORDER BY quoteCount DESC")
    fun recommendedAuthors(): DataSource.Factory<Int, AuthorEntity>

    @Query("SELECT * FROM AUTHORS_TABLE")
    fun authors(): DataSource.Factory<Int, AuthorEntity>

    @Query("DELETE FROM AUTHORS_TABLE")
    fun clearAuthors()


}