package com.pol0.maquotes.di

import com.pol0.domain.repository.AuthorsRepository
import com.pol0.domain.repository.FavouriteQuotesRepository
import com.pol0.domain.repository.QuotesRepository
import com.pol0.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesFetchQuotesUseCase(quotesRepository: QuotesRepository) : FetchQuotesUseCase {
        return FetchQuotesUseCase(quotesRepository)
    }
    @Provides
    @Singleton
    fun providesAddFavouriteQuotesUseCase(favouriteQuotesRepository: FavouriteQuotesRepository) : AddQuoteToFavouriteUseCase {
        return AddQuoteToFavouriteUseCase(favouriteQuotesRepository)
    }
    @Provides
    @Singleton
    fun providesGetFavouriteQuotesUseCase(favouriteQuotesRepository: FavouriteQuotesRepository) : GetFavouriteQuotesUseCase {
        return GetFavouriteQuotesUseCase(favouriteQuotesRepository)
    }

    @Provides
    @Singleton
    fun providesFetchAuthorsUseCase(authorsRepository: AuthorsRepository) : FetchAuthorsUseCase {
        return FetchAuthorsUseCase(authorsRepository)
    }

    @Provides
    @Singleton
    fun providesRecommendedAuthorsUseCase(authorsRepository: AuthorsRepository) : FetchRecommendedAuthorsUseCase {
        return FetchRecommendedAuthorsUseCase(authorsRepository)
    }

    @Provides
    @Singleton
    fun providesFetchQuotesByAuthorUseCase(quotesRepository: QuotesRepository) : FetchQuotesByAuthorUseCase {
        return FetchQuotesByAuthorUseCase(quotesRepository)
    }
}