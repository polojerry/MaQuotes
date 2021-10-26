package com.pol0.remote.mappers

import com.pol0.domain.models.Author
import com.pol0.domain.models.Quote
import com.pol0.remote.models.AuthorNetwork
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

fun AuthorNetwork.toDomain(): Author {
    return Author(
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

