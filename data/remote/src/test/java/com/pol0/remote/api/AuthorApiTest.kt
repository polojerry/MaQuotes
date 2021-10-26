package com.pol0.remote.api

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AuthorApiTest : BaseTest() {
    override fun setup() {
        super.setup()
    }

    @Test
    fun `when fetch authors is called, authors can be parsed`() = runBlocking {
        val expectedSize = 20
        val actual = authorsApi.fetchAuthors(1).results.size
        Truth.assertThat(expectedSize).isEqualTo(actual)
    }


    @Test
    fun `when fetch authors is called, correct authors are parsed`() = runBlocking {
        val expected = "-yBDYq_Vtnuw"
        val actual = authorsApi.fetchAuthors(1).results.first().id
        Truth.assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `when fetch recommended authors is called,  a list of authors ordered by quotesCount should be returned` () =
        runBlocking {
            val recommendedAuthors = authorsApi.fetchRecommendedAuthors(page = 2).results

            val topAuthor = recommendedAuthors[0]
            val secondTopAuthor = recommendedAuthors[1]

            Truth.assertThat(topAuthor.quoteCount).isGreaterThan(secondTopAuthor.quoteCount)

        }

}