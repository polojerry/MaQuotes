package com.pol0.repository.mappers

import com.pol0.local.models.QuoteEntity
import com.pol0.remote.models.AuthorNetwork
import com.pol0.remote.models.QuoteNetwork
import com.pol0.local.models.authors.AuthorEntity
import com.pol0.local.models.authors.RecommendedAuthorEntity

fun QuoteNetwork.toEntity(): QuoteEntity {
    return QuoteEntity(
        id,
        author,
        authorSlug,
        content,
        dateAdded,
        dateModified,
        length,
        tags,
    )
}

fun AuthorNetwork.toEntity() : AuthorEntity {
    return AuthorEntity(
        id,
        bio,
        dateAdded,
        dateModified,
        description,
        link,
        name,
        quoteCount,
        slug
    )
}

fun AuthorNetwork.toRecommendedAuthorEntity() : RecommendedAuthorEntity {
    return RecommendedAuthorEntity(
        id,
        bio,
        dateAdded,
        dateModified,
        description,
        link,
        name,
        quoteCount,
        slug
    )
}
