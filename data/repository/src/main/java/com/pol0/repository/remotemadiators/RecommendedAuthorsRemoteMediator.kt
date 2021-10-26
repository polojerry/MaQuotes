package com.pol0.repository.remotemadiators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.pol0.domain.models.Author
import com.pol0.local.database.QuotesDatabase
import com.pol0.local.models.authors.RecommendedAuthorsRemoteKeysEntity
import com.pol0.remote.api.AuthorsApi
import com.pol0.repository.mappers.toRecommendedAuthorEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RecommendedAuthorsRemoteMediator(
    private val authorsApi: AuthorsApi,
    private val quotesDatabase: QuotesDatabase
) : RemoteMediator<Int, Author>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Author>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: AUTHORS_STARTING_PAGE_NUMBER
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }

        try {
            val response = authorsApi.fetchRecommendedAuthors(page = page)
            val authors = response.results
            val endOfPaginationReached = authors.isEmpty()

            quotesDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    quotesDatabase.recommendedAuthorsRemoteKeysDao.clearRemoteKeys()
                    quotesDatabase.recommendedAuthorsDao.clearRecommendedAuthors()
                }

                val prevKey = if (page == AUTHORS_STARTING_PAGE_NUMBER) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = authors.map {
                    RecommendedAuthorsRemoteKeysEntity(
                        authorId = it.id,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                quotesDatabase.recommendedAuthorsRemoteKeysDao.insertAll(keys)
                quotesDatabase.recommendedAuthorsDao.insertRecommendedAuthors(authors = authors.map { it.toRecommendedAuthorEntity() })

            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Author>): RecommendedAuthorsRemoteKeysEntity? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { author ->
                // Get the remote keys of the last item retrieved
                quotesDatabase.recommendedAuthorsRemoteKeysDao.remoteKeysAuthorId(author.id)
            }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Author>
    ): RecommendedAuthorsRemoteKeysEntity? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { authorId ->
                quotesDatabase.recommendedAuthorsRemoteKeysDao.remoteKeysAuthorId(authorId)
            }
        }
    }


    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Author>): RecommendedAuthorsRemoteKeysEntity? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { author ->
                // Get the remote keys of the first items retrieved
                quotesDatabase.recommendedAuthorsRemoteKeysDao.remoteKeysAuthorId(author.id)
            }
    }

    companion object {
        const val AUTHORS_STARTING_PAGE_NUMBER = 1
    }
}