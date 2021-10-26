package com.pol0.domain.usecases

import androidx.paging.PagingData
import com.pol0.domain.models.Quote
import com.pol0.domain.repository.QuotesRepository
import com.pol0.domain.usecases.base.FlowBaseUseCase

import kotlinx.coroutines.flow.Flow

typealias FetchQuotesBaseUseCase = FlowBaseUseCase<Unit, Flow<PagingData<Quote>>>

class FetchQuotesUseCase constructor(private val quotesRepository: QuotesRepository) :
    FetchQuotesBaseUseCase {
    override fun invoke(params: Unit): Flow<PagingData<Quote>> {
        return quotesRepository.fetchQuotes()
    }

}