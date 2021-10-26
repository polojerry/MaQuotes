package com.pol0.repository.di

import com.pol0.domain.repository.QuotesRepository
import com.pol0.local.database.QuotesDatabase
import com.pol0.remote.api.QuoteApi
import com.pol0.repository.QuoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesQuotesRepository(quoteApi: QuoteApi, quotesDatabase: QuotesDatabase): QuotesRepository {
        return QuoteRepositoryImpl(quoteApi, quotesDatabase)
    }
}