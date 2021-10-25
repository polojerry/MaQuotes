package com.pol0.remote.responses

import com.pol0.remote.models.QuoteNetwork

data class QuoteResponse(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<QuoteNetwork>,
    val totalCount: Int,
    val totalPages: Int
)