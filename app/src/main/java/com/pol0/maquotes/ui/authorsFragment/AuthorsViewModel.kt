package com.pol0.maquotes.ui.authorsFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.pol0.domain.usecases.FetchAuthorsUseCase
import com.pol0.domain.usecases.FetchRecommendedAuthorsUseCase
import com.pol0.maquotes.mappers.toPresentation
import com.pol0.maquotes.model.AuthorPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorsViewModel @Inject constructor(
    private val fetchAuthorsUseCase: FetchAuthorsUseCase,
    private val fetchRecommendedAuthorsUseCase: FetchRecommendedAuthorsUseCase
) : ViewModel() {

    private val mutableAuthors =
        MutableStateFlow<AuthorsUiState>(AuthorsUiState.StandBy)
    val authors: StateFlow<AuthorsUiState>
        get() = mutableAuthors.asStateFlow()

    private val mutableRecommendedAuthors =
        MutableStateFlow<AuthorsUiState>(AuthorsUiState.StandBy)
    val recommendedAuthors: StateFlow<AuthorsUiState>
        get() = mutableRecommendedAuthors.asStateFlow()


    init {
        fetchAuthors()
        fetchRecommendedAuthor()
    }

    private fun fetchAuthors() = viewModelScope.launch {
        fetchAuthorsUseCase(Unit)
            .map {
                it.map { author ->
                    author.toPresentation()
                }
            }
            .cachedIn(this)
            .collect {
                mutableAuthors.emit(AuthorsUiState.LoadedData(it))
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
                mutableRecommendedAuthors.emit(AuthorsUiState.LoadedData(it))
            }
    }


    sealed class AuthorsUiState {
        data class LoadedData(val pagedAuthors: PagingData<AuthorPresentation>) :
            AuthorsUiState()

        object StandBy : AuthorsUiState()
    }

}