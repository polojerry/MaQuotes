package com.pol0.domain.repository

import androidx.paging.PagingData
import com.pol0.domain.models.Author
import kotlinx.coroutines.flow.Flow

interface AuthorsRepository {

    fun fetchAuthors(): Flow<PagingData<Author>>

    fun fetchRecommendedAuthors(): Flow<PagingData<Author>>
}