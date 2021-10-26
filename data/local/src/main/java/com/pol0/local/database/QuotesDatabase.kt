package com.pol0.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pol0.local.dao.authors.AuthorsDao
import com.pol0.local.dao.authors.AuthorsRemoteKeysDao
import com.pol0.local.dao.authors.RecommendedAuthorsDao
import com.pol0.local.dao.authors.RecommendedAuthorsRemoteKeysDao
import com.pol0.local.dao.quotes.FavouriteQuotesDao
import com.pol0.local.dao.quotes.QuotesDao
import com.pol0.local.dao.quotes.QuotesRemoteKeysDao
import com.pol0.local.models.FavouriteQuoteEntity
import com.pol0.local.models.QuoteEntity
import com.pol0.local.models.QuotesRemoteKeysEntity
import com.pol0.local.models.authors.AuthorEntity
import com.pol0.local.models.authors.AuthorsRemoteKeysEntity
import com.pol0.local.models.authors.RecommendedAuthorEntity
import com.pol0.local.models.authors.RecommendedAuthorsRemoteKeysEntity
import com.pol0.local.typeconverters.TagsConverter

@Database(
    entities = [
        QuoteEntity::class, QuotesRemoteKeysEntity::class, FavouriteQuoteEntity::class,
        AuthorEntity::class, AuthorsRemoteKeysEntity::class,
        RecommendedAuthorEntity::class, RecommendedAuthorsRemoteKeysEntity::class
    ],
    version = 2,
    exportSchema = false,
)
@TypeConverters(TagsConverter::class)
abstract class QuotesDatabase : RoomDatabase() {
    abstract val quotesDao: QuotesDao
    abstract val quotesRemoteKeysDao: QuotesRemoteKeysDao
    abstract val favouriteQuotesDao: FavouriteQuotesDao
    abstract val authorsDao: AuthorsDao
    abstract val authorsRemoteKeysDao: AuthorsRemoteKeysDao
    abstract val recommendedAuthorsDao: RecommendedAuthorsDao
    abstract val recommendedAuthorsRemoteKeysDao: RecommendedAuthorsRemoteKeysDao
}