package com.pol0.remote.mappers

import com.google.common.truth.Truth
import com.pol0.remote.fakes.DataObjects
import com.pol0.remote.mappers.toDomain
import org.junit.Test

class NetworkToDomainMapperTest {

    @Test
    fun `mapping network model to domain model should return the correct model `() {
        val expected = DataObjects.quote

        val actual = DataObjects.networkQuote.toDomain()

        Truth.assertThat(actual).isEqualTo(expected)
    }
}