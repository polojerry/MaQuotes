package com.pol0.remote.api

import com.pol0.remote.responses.AuthorsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthorsApi {

    @GET("authors")
    suspend fun fetchAuthors(
        @Query("page") page: Int,
    ): AuthorsResponse

    @GET("authors")
    suspend fun fetchRecommendedAuthors(
        @Query("sortBy") sortBy: String = "quoteCount",
        @Query("order") order: String = "desc",
        @Query("page") page: Int
    ): AuthorsResponse

}