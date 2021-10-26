package com.pol0.domain.usecases

import androidx.paging.PagingData
import com.pol0.domain.models.Quote
import com.pol0.domain.repository.QuotesRepository

import kotlinx.coroutines.flow.Flow

typealias GetFavouriteQuotesBaseUseCase = FlowBaseUseCase<Unit, Flow<PagingData<Quote>>>

class GetFavouriteQuotesUseCase constructor(private val quotesRepository: QuotesRepository) :
    GetFavouriteQuotesBaseUseCase {
    override fun invoke(params: Unit): Flow<PagingData<Quote>> {
        return quotesRepository.getFavouriteQuotes()
    }

}