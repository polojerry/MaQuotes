package com.pol0.maquotes.mappers

import com.pol0.domain.models.FavouriteQuote
import com.pol0.domain.models.Quote
import com.pol0.maquotes.model.QuotePresentation

fun Quote.toPresentation(): QuotePresentation {
    return QuotePresentation(
        id,
        author,
        authorSlug,
        content,
        dateAdded,
        dateModified,
        length,
        tags,
        isFavourite = false
    )
}
fun FavouriteQuote.toPresentation(): QuotePresentation {
    return QuotePresentation(
        id,
        author,
        authorSlug,
        content,
        dateAdded,
        dateModified,
        length,
        tags,
        isFavourite = true
    )
}
