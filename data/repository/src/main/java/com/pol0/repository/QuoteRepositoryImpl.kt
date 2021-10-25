package com.pol0.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pol0.domain.models.Quote
import com.pol0.domain.repository.QuotesRepository
import com.pol0.remote.api.QuoteApi
import com.pol0.repository.paggingsource.QuotePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteApi: QuoteApi,
) : QuotesRepository {

    override fun fetchQuotes(): Flow<PagingData<Quote>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { QuotePagingSource(quoteApi) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }

}