package com.pol0.domain.usecases

import com.pol0.domain.models.Quote
import com.pol0.domain.repository.FavouriteQuotesRepository
import com.pol0.domain.repository.QuotesRepository
import com.pol0.domain.usecases.base.BaseUseCase
import com.pol0.domain.usecases.base.FlowBaseUseCase

typealias AddQuoteToFavouriteBaseUseCase = BaseUseCase<Quote, Long>

class AddQuoteToFavouriteUseCase constructor(private val favouriteQuotesRepository: FavouriteQuotesRepository) :
    AddQuoteToFavouriteBaseUseCase {
    override suspend fun invoke(params: Quote): Long {
        return favouriteQuotesRepository.addFavouriteQuote(params)
    }

}