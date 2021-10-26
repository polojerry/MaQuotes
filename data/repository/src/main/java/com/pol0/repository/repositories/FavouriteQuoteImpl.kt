package com.pol0.repository.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pol0.domain.models.FavouriteQuote
import com.pol0.domain.models.Quote
import com.pol0.domain.repository.FavouriteQuotesRepository
import com.pol0.local.database.QuotesDatabase
import com.pol0.local.mappers.toDomain
import com.pol0.local.mappers.toFavourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteQuoteImpl @Inject constructor(
    private val quotesDatabase: QuotesDatabase
) : FavouriteQuotesRepository {

    override fun getFavouriteQuotes(): Flow<PagingData<FavouriteQuote>> {

        val pagingSourceFactory =
            quotesDatabase.favouriteQuotesDao.favouriteQuotes().map {
                it.toDomain()
            }.asPagingSourceFactory()


        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = FAVOURITE_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun addFavouriteQuote(quote: Quote): Long {
        return quotesDatabase.favouriteQuotesDao.insertFavouriteQuote(quote.toFavourite())
    }

    companion object {
        const val FAVOURITE_PAGE_SIZE = 4
    }

}
