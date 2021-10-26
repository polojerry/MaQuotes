package com.pol0.domain.repository

import androidx.paging.PagingData
import com.pol0.domain.models.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {

    fun fetchQuotes(): Flow<PagingData<Quote>>

    fun getFavouriteQuotes(): Flow<PagingData<Quote>>

    fun addFavouriteQuote(quote: Quote): Long
}