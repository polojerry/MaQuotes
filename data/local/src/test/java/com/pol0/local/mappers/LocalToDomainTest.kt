package com.pol0.local.mappers

import com.google.common.truth.Truth
import com.pol0.local.fakes.DataObjects
import org.junit.Test

class LocalToDomainTest {

    @Test
    fun `when toDomain is called on QuoteEntity Object, it should be converted to a corresponding domain object` () {
        val expected = DataObjects.Quote.quote

        val actual = DataObjects.Quote.quoteEntity.toDomain()

        Truth.assertThat(expected).isEqualTo(actual)

    }

    @Test
    fun `when toDomain is called on AuthorEntity Object, it should be converted to a corresponding domain object` () {
        val expected = DataObjects.Author.author

        val actual = DataObjects.Author.authorLocal.toDomain()

        Truth.assertThat(expected).isEqualTo(actual)

    }
}