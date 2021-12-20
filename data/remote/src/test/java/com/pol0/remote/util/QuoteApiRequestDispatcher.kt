package com.pol0.remote.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

internal class QuoteApiRequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/quotes?page=6" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(MockResponseFileReader("json_responses/quotes_page_6_response.json").content)
            }

            "/authors?page=1" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(MockResponseFileReader("json_responses/authors_page_1_response.json").content)
            }

            "/authors?sortBy=quoteCount&order=desc&page=2" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(MockResponseFileReader("json_responses/recommended_authors_page_2.json").content)
            }

            else -> throw IllegalArgumentException("Unknown Request Path ${request.path.toString()}")
        }
    }

}