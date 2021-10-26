package com.pol0.remote.fakes

import com.pol0.domain.models.Author
import com.pol0.domain.models.Quote
import com.pol0.remote.models.AuthorNetwork
import com.pol0.remote.models.QuoteNetwork

object DataObjects {

    object Quote {
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

    object Author {
        val authorNetwork = AuthorNetwork(
            link = "https://en.wikipedia.org/wiki/Gautama_Buddha",
            bio = "The Buddha, was a philosopher, mendicant, meditator, and spiritual teacher who lived in ancient India. The world religion of Buddhism was founded by the efforts of him and his followers. He taught for around forty years and built a large following, both monastic and lay.",
            description = "Founder of Buddhism",
            id = "sUNjshHENA05",
            name = "Buddha",
            quoteCount = 57,
            slug = "buddha",
            dateAdded = "2020-03-15",
            dateModified = "2020-03-15"
        )

        val author = Author(
            link = "https://en.wikipedia.org/wiki/Gautama_Buddha",
            bio = "The Buddha, was a philosopher, mendicant, meditator, and spiritual teacher who lived in ancient India. The world religion of Buddhism was founded by the efforts of him and his followers. He taught for around forty years and built a large following, both monastic and lay.",
            description = "Founder of Buddhism",
            id = "sUNjshHENA05",
            name = "Buddha",
            quoteCount = 57,
            slug = "buddha",
            dateAdded = "2020-03-15",
            dateModified = "2020-03-15"
        )
    }

}