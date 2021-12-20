package com.pol0.maquotes.ui.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.pol0.domain.usecases.AddQuoteToFavouriteUseCase
import com.pol0.domain.usecases.FetchQuotesUseCase
import com.pol0.domain.usecases.FetchRecommendedAuthorsUseCase
import com.pol0.maquotes.mappers.toDomain
import com.pol0.maquotes.mappers.toPresentation
import com.pol0.maquotes.model.AuthorPresentation
import com.pol0.maquotes.model.QuotePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchQuotesUseCase: FetchQuotesUseCase,
    private val addQuoteToFavouriteUseCase: AddQuoteToFavouriteUseCase,
    private val fetchRecommendedAuthorsUseCase: FetchRecommendedAuthorsUseCase,
) : ViewModel() {

    private val mutableQuotes = MutableStateFlow<QuoteUiState>(QuoteUiState.StandBy)
    val quotes: StateFlow<QuoteUiState>
        get() = mutableQuotes.asStateFlow()

    private val mutableRecommendedAuthors =
        MutableStateFlow<RecommendedUiState>(RecommendedUiState.StandBy)
    val recommendedAuthors: StateFlow<RecommendedUiState>
        get() = mutableRecommendedAuthors.asStateFlow()

    init {
        fetchQuotes()
        fetchRecommendedAuthor()
    }

    private fun fetchQuotes() = viewModelScope.launch {
        fetchQuotesUseCase(Unit)
            .map {
                it.map { quote ->
                    quote.toPresentation()
                }
            }
            .cachedIn(this)
            .collect {
                mutableQuotes.emit(QuoteUiState.LoadedData(it))
            }
    }

    private fun fetchRecommendedAuthor() = viewModelScope.launch {
        fetchRecommendedAuthorsUseCase(Unit)
            .map {
                it.map { author ->
                    author.toPresentation()
                }
            }
            .cachedIn(this)
            .collect {
                mutableRecommendedAuthors.emit(RecommendedUiState.LoadedData(it))
            }
    }

    fun addToFavourite(quote: QuotePresentation): Flow<Long> = flow {
        val rows = addQuoteToFavouriteUseCase(quote.toDomain())
        emit(rows)
    }

    sealed class QuoteUiState {
        data class LoadedData(val pagedQuotes: PagingData<QuotePresentation>) : QuoteUiState()
        object StandBy : QuoteUiState()
    }

    sealed class RecommendedUiState {
        data class LoadedData(val pagedAuthors: PagingData<AuthorPresentation>) :
            RecommendedUiState()

        object StandBy : RecommendedUiState()
    }

}