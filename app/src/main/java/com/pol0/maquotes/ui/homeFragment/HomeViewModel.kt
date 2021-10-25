package com.pol0.maquotes.ui.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pol0.domain.models.Quote
import com.pol0.domain.usecases.FetchQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchQuotesUseCase: FetchQuotesUseCase,
) : ViewModel() {

    private val mutableQuotes = MutableStateFlow<QuoteUiState>(QuoteUiState.StandBy)
    val quotes: StateFlow<QuoteUiState>
        get() = mutableQuotes.asStateFlow()

    init {
        fetchQuotes()
    }

    private fun fetchQuotes() = viewModelScope.launch {
        fetchQuotesUseCase(Unit)
            .cachedIn(this)
            .collect {
                mutableQuotes.emit(QuoteUiState.LoadedData(it))
            }
    }

    sealed class QuoteUiState {
        data class LoadedData(val pagedQuotes: PagingData<Quote>) : QuoteUiState()
        object StandBy : QuoteUiState()
    }

}