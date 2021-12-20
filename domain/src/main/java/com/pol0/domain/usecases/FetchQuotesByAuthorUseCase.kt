package com.pol0.domain.usecases

import androidx.paging.PagingData
import com.pol0.domain.models.Quote
import com.pol0.domain.repository.QuotesRepository
import com.pol0.domain.usecases.base.FlowBaseUseCase
import kotlinx.coroutines.flow.Flow

typealias FetchQuotesByAuthorBaseUseCase = FlowBaseUseCase<String, Flow<PagingData<Quote>>>

class FetchQuotesByAuthorUseCase constructor(private val quotesRepository: QuotesRepository) :
    FetchQuotesByAuthorBaseUseCase {
    override fun invoke(params: String): Flow<PagingData<Quote>> {
        return quotesRepository.fetchQuotesByAuthor(params)
    }
}