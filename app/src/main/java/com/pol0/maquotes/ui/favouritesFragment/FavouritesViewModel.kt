package com.pol0.maquotes.ui.favouritesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.pol0.domain.usecases.AddQuoteToFavouriteUseCase
import com.pol0.domain.usecases.GetFavouriteQuotesUseCase
import com.pol0.maquotes.mappers.toPresentation
import com.pol0.maquotes.model.QuotePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouriteQuotesUseCase: GetFavouriteQuotesUseCase,
    private val addQuoteToFavouriteUseCase: AddQuoteToFavouriteUseCase,
) : ViewModel() {

    private val mutableQuotes =
        MutableStateFlow<FavouriteQuoteUiState>(FavouriteQuoteUiState.StandBy)
    val favouriteQuotes: StateFlow<FavouriteQuoteUiState>
        get() = mutableQuotes.asStateFlow()


    init {
        fetchFavouriteQuotes()
    }

    private fun fetchFavouriteQuotes() = viewModelScope.launch {
        getFavouriteQuotesUseCase(Unit)
            .map {
                it.map { favouriteQuote ->
                    favouriteQuote.toPresentation()
                }
            }
            .cachedIn(this)
            .collect {
                mutableQuotes.emit(FavouriteQuoteUiState.LoadedData(it))
            }
    }

    sealed class FavouriteQuoteUiState {
        data class LoadedData(val pagedQuotes: PagingData<QuotePresentation>) :
            FavouriteQuoteUiState()

        object StandBy : FavouriteQuoteUiState()
    }

}