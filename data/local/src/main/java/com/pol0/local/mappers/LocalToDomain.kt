package com.pol0.local.mappers

import com.pol0.domain.models.FavouriteQuote
import com.pol0.domain.models.Quote
import com.pol0.local.models.FavouriteQuoteEntity
import com.pol0.local.models.QuoteEntity

fun QuoteEntity.toDomain(): Quote {
    return Quote(
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

fun FavouriteQuoteEntity.toDomain(): FavouriteQuote {
    return FavouriteQuote(
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

fun Quote.toFavourite(): FavouriteQuoteEntity {
    return FavouriteQuoteEntity(
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
