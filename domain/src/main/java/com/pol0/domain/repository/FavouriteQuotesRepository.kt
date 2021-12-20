package com.pol0.domain.repository

import androidx.paging.PagingData
import com.pol0.domain.models.FavouriteQuote
import com.pol0.domain.models.Quote
import kotlinx.coroutines.flow.Flow

interface FavouriteQuotesRepository {
    fun getFavouriteQuotes(): Flow<PagingData<FavouriteQuote>>

    suspend fun addFavouriteQuote(quote: Quote): Long
}