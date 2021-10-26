package com.pol0.remote.responses

import com.pol0.remote.models.AuthorNetwork

data class AuthorsResponse(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<AuthorNetwork>,
    val totalCount: Int,
    val totalPages: Int
)