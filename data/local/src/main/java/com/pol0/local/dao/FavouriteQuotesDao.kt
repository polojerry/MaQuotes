package com.pol0.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pol0.local.models.FavouriteQuoteEntity
import com.pol0.local.models.QuoteEntity

@Dao
interface FavouriteQuotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteQuote(quote: FavouriteQuoteEntity) : Long

    @Query("SELECT * FROM FAVOURITE_QUOTE_TABLE")
    fun favouriteQuotes(): DataSource.Factory<Int, FavouriteQuoteEntity>

    @Query("DELETE FROM FAVOURITE_QUOTE_TABLE")
    fun clearFavourites()

}