package com.pol0.remote.api

import com.pol0.remote.responses.QuoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("quotes")
    suspend fun fetchQuotes(
        @Query("page") page: Int,
    ): QuoteResponse
}