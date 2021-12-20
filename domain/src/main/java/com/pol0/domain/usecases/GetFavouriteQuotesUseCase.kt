package com.pol0.domain.usecases

import androidx.paging.PagingData
import com.pol0.domain.models.FavouriteQuote
import com.pol0.domain.models.Quote
import com.pol0.domain.repository.FavouriteQuotesRepository
import com.pol0.domain.repository.QuotesRepository
import com.pol0.domain.usecases.base.FlowBaseUseCase

import kotlinx.coroutines.flow.Flow

typealias GetFavouriteQuotesBaseUseCase = FlowBaseUseCase<Unit, Flow<PagingData<FavouriteQuote>>>

class GetFavouriteQuotesUseCase constructor(private val favouriteQuotesRepository: FavouriteQuotesRepository) :
    GetFavouriteQuotesBaseUseCase {
    override fun invoke(params: Unit): Flow<PagingData<FavouriteQuote>> {
        return favouriteQuotesRepository.getFavouriteQuotes()
    }

}