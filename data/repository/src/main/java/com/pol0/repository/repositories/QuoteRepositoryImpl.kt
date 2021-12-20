package com.pol0.repository.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pol0.domain.models.Quote
import com.pol0.domain.repository.QuotesRepository
import com.pol0.local.database.QuotesDatabase
import com.pol0.local.mappers.toDomain
import com.pol0.remote.api.QuoteApi
import com.pol0.repository.remotemadiators.QuotesRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteApi: QuoteApi,
    private val quotesDatabase: QuotesDatabase,
) : QuotesRepository {

    override fun fetchQuotes(): Flow<PagingData<Quote>> {

        val pagingSourceFactory =
            quotesDatabase.quotesDao.quotes().map {
                it.toDomain()
            }.asPagingSourceFactory()

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = QuotesRemoteMediator(
                quoteApi,
                quotesDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun fetchQuotesByAuthor(authorSlug: String): Flow<PagingData<Quote>> {
        val pagingSourceFactory =
            quotesDatabase.quotesDao.quotesByAuthor(authorSlug).map {
                it.toDomain()
            }.asPagingSourceFactory()

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = LOCAL_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
        const val LOCAL_PAGE_SIZE = 30
    }

}