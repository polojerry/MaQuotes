package com.pol0.repository.mappers

import com.pol0.local.models.QuoteEntity
import com.pol0.remote.models.QuoteNetwork

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