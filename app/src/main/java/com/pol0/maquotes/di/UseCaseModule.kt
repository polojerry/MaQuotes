package com.pol0.maquotes.di

import com.pol0.domain.repository.QuotesRepository
import com.pol0.domain.usecases.FetchQuotesUseCase
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
}