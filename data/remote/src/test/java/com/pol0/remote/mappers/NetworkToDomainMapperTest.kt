package com.pol0.remote.mappers

import com.google.common.truth.Truth
import com.pol0.remote.fakes.DataObjects
import com.pol0.remote.mappers.toDomain
import org.junit.Test

class NetworkToDomainMapperTest {

    @Test
    fun `mapping quote network model to domain model should return the correct model `() {
        val expected = DataObjects.Quote.quote

        val actual = DataObjects.Quote.networkQuote.toDomain()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `mapping author network model to domain model should return the correct model `() {
        val expected = DataObjects.Author.author

        val actual = DataObjects.Author.authorNetwork.toDomain()

        Truth.assertThat(actual).isEqualTo(expected)
    }
}