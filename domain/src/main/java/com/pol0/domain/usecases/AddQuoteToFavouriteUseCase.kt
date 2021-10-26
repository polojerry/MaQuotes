package com.pol0.domain.usecases

import androidx.paging.PagingData
import com.pol0.domain.models.Quote
import com.pol0.domain.repository.QuotesRepository

import kotlinx.coroutines.flow.Flow

typealias AddQuoteToFavouriteBaseUseCase = FlowBaseUseCase<Quote, Long>

class AddQuoteToFavouriteUseCase constructor(private val quotesRepository: QuotesRepository) :
    AddQuoteToFavouriteBaseUseCase {
    override fun invoke(params: Quote): Long {
        return quotesRepository.addFavouriteQuote(params)
    }

}