package com.pol0.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pol0.local.models.QuoteEntity

@Dao
interface QuotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(quotes: List<QuoteEntity>)

    @Query("SELECT * FROM QUOTES_TABLE")
    fun quotes(): DataSource.Factory<Int, QuoteEntity>

    @Query("DELETE FROM QUOTES_TABLE")
    fun clearQuotes()

    @Query("UPDATE QUOTES_TABLE SET isFavourite=:isFavourite WHERE id=:quoteId ")
    fun toggleFavourite(quoteId: String, isFavourite: Boolean) : Long

    @Query("SELECT * FROM QUOTES_TABLE WHERE isFavourite = :favourite")
    fun favouriteQuotes(favourite: Boolean = true): DataSource.Factory<Int, QuoteEntity>

}