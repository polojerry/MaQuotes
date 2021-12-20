package com.pol0.remote.api

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class QuoteApiTest : BaseTest() {
    override fun setup() {
        super.setup()
    }

    @Test
    fun `when fetch quotes is called, quotes can be parsed`() = runBlocking {
        val expectedSize = 20
        val actual = quoteApi.fetchQuotes(6).results.size
        Truth.assertThat(expectedSize).isEqualTo(actual)
    }


    @Test
    fun `when fetch quotes is called, correct quotes can be parsed`() = runBlocking {
        val expected = "6vrEW1dd1Q"
        val actual = quoteApi.fetchQuotes(6).results.first().id
        Truth.assertThat(expected).isEqualTo(actual)
    }

}