package com.pol0.maquotes.ui.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.pol0.domain.models.Quote
import com.pol0.domain.usecases.AddQuoteToFavouriteUseCase
import com.pol0.domain.usecases.FetchQuotesUseCase
import com.pol0.maquotes.mappers.toDomain
import com.pol0.maquotes.mappers.toPresentation
import com.pol0.maquotes.model.QuotePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchQuotesUseCase: FetchQuotesUseCase,
    private val addQuoteToFavouriteUseCase: AddQuoteToFavouriteUseCase,
) : ViewModel() {

    private val mutableQuotes = MutableStateFlow<QuoteUiState>(QuoteUiState.StandBy)
    val quotes: StateFlow<QuoteUiState>
        get() = mutableQuotes.asStateFlow()

    init {
        fetchQuotes()
    }

    private fun fetchQuotes() = viewModelScope.launch {
        fetchQuotesUseCase(Unit)
            .map { it.map { quote->
                quote.toPresentation() }
            }
            .cachedIn(this)
            .collect {
                mutableQuotes.emit(QuoteUiState.LoadedData(it))
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

}