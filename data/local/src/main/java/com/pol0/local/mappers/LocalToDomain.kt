package com.pol0.local.mappers

import com.pol0.domain.models.Quote
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
        isFavourite
    )
}
