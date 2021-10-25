package com.pol0.remote.fakes

import com.pol0.domain.models.Quote
import com.pol0.remote.models.QuoteNetwork

object DataObjects {

    val networkQuote = QuoteNetwork(
        id = "4nWz20xbUO1H",
        content = "If facts are the seeds that later produce knowledge and wisdom, then the emotions and the impressions of the senses are the fertile soil in which the seeds must grow.",
        author = "Rachel Carson",
        authorSlug = "rachel-carson",
        length = 166,
        dateAdded = "2021-04-15",
        dateModified = "2021-04-15",
        tags = listOf("famous-quotes")
    )

    val quote = Quote(
        id = "4nWz20xbUO1H",
        content = "If facts are the seeds that later produce knowledge and wisdom, then the emotions and the impressions of the senses are the fertile soil in which the seeds must grow.",
        author = "Rachel Carson",
        authorSlug = "rachel-carson",
        length = 166,
        dateAdded = "2021-04-15",
        dateModified = "2021-04-15",
        tags = listOf("famous-quotes")
    )
}