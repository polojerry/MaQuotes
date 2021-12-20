package com.pol0.maquotes.mappers

import com.pol0.domain.models.Quote
import com.pol0.maquotes.model.QuotePresentation

fun QuotePresentation.toDomain(): Quote {
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

