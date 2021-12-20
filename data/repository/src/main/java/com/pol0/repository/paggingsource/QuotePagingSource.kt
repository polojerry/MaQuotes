package com.pol0.repository.paggingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pol0.domain.models.Quote
import com.pol0.remote.api.QuoteApi
import com.pol0.remote.mappers.toDomain
import retrofit2.HttpException
import java.io.IOException

class QuotePagingSource constructor(
    private val quoteApi: QuoteApi,
) : PagingSource<Int, Quote>() {
    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1) ?: state.closestPageToPosition(
                position
            )?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        val position = params.key ?: QUOTE_STARTING_PAGE_NUMBER
        return try {
            val response = quoteApi.fetchQuotes(position)
            val quotes = response.results.map {
                it.toDomain()
            }
            val nextKey = if (quotes.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            LoadResult.Page(
                data = quotes,
                prevKey = if (position == QUOTE_STARTING_PAGE_NUMBER) null else (position - 1),
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val QUOTE_STARTING_PAGE_NUMBER = 1
        const val NETWORK_PAGE_SIZE = 30
    }
}
