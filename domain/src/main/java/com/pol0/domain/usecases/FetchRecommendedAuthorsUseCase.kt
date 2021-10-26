package com.pol0.domain.usecases

import androidx.paging.PagingData
import com.pol0.domain.models.Author
import com.pol0.domain.repository.AuthorsRepository
import com.pol0.domain.usecases.base.FlowBaseUseCase
import kotlinx.coroutines.flow.Flow

typealias FetchRecommendedAuthorsBaseUseCase = FlowBaseUseCase<Unit, Flow<PagingData<Author>>>

class FetchRecommendedAuthorsUseCase constructor(private val authorsRepository: AuthorsRepository) :
    FetchRecommendedAuthorsBaseUseCase {
    override fun invoke(params: Unit): Flow<PagingData<Author>> {
        return authorsRepository.fetchRecommendedAuthors()
    }
}