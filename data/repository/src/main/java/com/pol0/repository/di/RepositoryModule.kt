package com.pol0.repository.di

import com.pol0.domain.repository.AuthorsRepository
import com.pol0.domain.repository.FavouriteQuotesRepository
import com.pol0.domain.repository.QuotesRepository
import com.pol0.local.database.QuotesDatabase
import com.pol0.remote.api.AuthorsApi
import com.pol0.remote.api.QuoteApi
import com.pol0.repository.repositories.AuthorsRepositoryImpl
import com.pol0.repository.repositories.FavouriteQuoteImpl
import com.pol0.repository.repositories.QuoteRepositoryImpl
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

    @Provides
    @Singleton
    fun providesFavouriteQuotesRepository(quotesDatabase: QuotesDatabase): FavouriteQuotesRepository {
        return FavouriteQuoteImpl(quotesDatabase)
    }

    @Provides
    @Singleton
    fun providesAuthorsRepository(authorsApi: AuthorsApi, quotesDatabase: QuotesDatabase): AuthorsRepository {
        return AuthorsRepositoryImpl(authorsApi, quotesDatabase)
    }
}