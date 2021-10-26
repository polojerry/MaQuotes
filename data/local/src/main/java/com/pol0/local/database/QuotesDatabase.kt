package com.pol0.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pol0.local.dao.FavouriteQuotesDao
import com.pol0.local.dao.QuotesDao
import com.pol0.local.dao.QuotesRemoteKeysDao
import com.pol0.local.models.FavouriteQuoteEntity
import com.pol0.local.models.QuoteEntity
import com.pol0.local.models.QuotesRemoteKeysEntity
import com.pol0.local.typeconverters.TagsConverter

@Database(
    entities = [QuoteEntity::class, QuotesRemoteKeysEntity::class,FavouriteQuoteEntity::class],
    version = 2,
    exportSchema = false,
)
@TypeConverters(TagsConverter::class)
abstract class QuotesDatabase : RoomDatabase() {
    abstract val quotesDao: QuotesDao
    abstract val quotesRemoteKeysDao: QuotesRemoteKeysDao
    abstract val favouriteQuotesDao  :FavouriteQuotesDao
}