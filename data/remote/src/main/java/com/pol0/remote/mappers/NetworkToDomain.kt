package com.pol0.remote.mappers

import com.pol0.domain.models.Quote
import com.pol0.remote.models.QuoteNetwork


fun QuoteNetwork.toDomain(): Quote {
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
