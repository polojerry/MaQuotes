package com.pol0.repository.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pol0.domain.models.Author
import com.pol0.domain.repository.AuthorsRepository
import com.pol0.local.database.QuotesDatabase
import com.pol0.local.mappers.toDomain
import com.pol0.remote.api.AuthorsApi
import com.pol0.repository.paggingsource.RecommendedAuthorsPagingSource
import com.pol0.repository.remotemadiators.AuthorsRemoteMediator
import com.pol0.repository.remotemadiators.RecommendedAuthorsRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthorsRepositoryImpl @Inject constructor(
    private val authorsApi: AuthorsApi,
    private val quotesDatabase: QuotesDatabase,
) : AuthorsRepository {

    override fun fetchAuthors(): Flow<PagingData<Author>> {
        val pagingSourceFactory =
            quotesDatabase.authorsDao.authors().map {
                it.toDomain()
            }.asPagingSourceFactory()

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = AuthorsRemoteMediator(
                authorsApi,
                quotesDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun fetchRecommendedAuthors(): Flow<PagingData<Author>> {
        /*@OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { RecommendedAuthorsPagingSource(authorsApi) }
        ).flow*/

        val pagingSourceFactory =
            quotesDatabase.recommendedAuthorsDao.recommendedAuthors().map {
                it.toDomain()
            }.asPagingSourceFactory()

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = RecommendedAuthorsRemoteMediator(
                authorsApi,
                quotesDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow

    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}