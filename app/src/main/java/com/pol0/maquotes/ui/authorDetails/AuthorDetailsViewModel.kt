package com.pol0.maquotes.ui.authorDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.pol0.domain.usecases.FetchQuotesByAuthorUseCase
import com.pol0.maquotes.mappers.toPresentation
import com.pol0.maquotes.model.AuthorPresentation
import com.pol0.maquotes.ui.favouritesFragment.FavouritesViewModel
import com.pol0.maquotes.ui.homeFragment.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorDetailsViewModel @Inject constructor(
    private val fetchQuotesByAuthorUseCase: FetchQuotesByAuthorUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mutableAuthor = MutableStateFlow<AuthorPresentation?>(null)
    val author: StateFlow<AuthorPresentation?>
        get() = mutableAuthor.asStateFlow()

    private val mutableFamousQuotes =
        MutableStateFlow<HomeViewModel.QuoteUiState>(HomeViewModel.QuoteUiState.StandBy)
    val famousQuotes: StateFlow<HomeViewModel.QuoteUiState>
        get() = mutableFamousQuotes.asStateFlow()


    init {
        mutableAuthor.value = (savedStateHandle.get("author"))
        fetchFamousQuotes()
    }

    private fun fetchFamousQuotes() = viewModelScope.launch {
        fetchQuotesByAuthorUseCase(author.value!!.slug)
            .map {
                it.map { favouriteQuote ->
                    favouriteQuote.toPresentation()
                }
            }
            .cachedIn(this)
            .collect {
                mutableFamousQuotes.emit(HomeViewModel.QuoteUiState.LoadedData(it))
            }
    }

}