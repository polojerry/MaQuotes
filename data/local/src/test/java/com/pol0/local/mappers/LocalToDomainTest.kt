package com.pol0.local.mappers

import com.google.common.truth.Truth
import com.pol0.local.fakes.DataObjects
import org.junit.Test

class LocalToDomainTest {

    @Test
    fun `when toDomain is called on QuoteEntity Object, it should be converted to a corresponding domain object` () {
        val expected = DataObjects.quote

        val actual = DataObjects.quoteEntity.toDomain()

        Truth.assertThat(expected).isEqualTo(actual)

    }
}