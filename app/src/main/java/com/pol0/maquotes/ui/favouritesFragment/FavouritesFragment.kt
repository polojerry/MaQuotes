package com.pol0.maquotes.ui.favouritesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.pol0.maquotes.adapters.QuoteAdapterOutlined
import com.pol0.maquotes.databinding.FavouritesFragmentBinding
import com.pol0.maquotes.model.QuotePresentation
import com.pol0.maquotes.ui.favouritesFragment.FavouritesViewModel.FavouriteQuoteUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private val viewModel: FavouritesViewModel by viewModels()

    private lateinit var binding: FavouritesFragmentBinding
    private lateinit var favouriteQuoteAdapter: QuoteAdapterOutlined

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavouritesFragmentBinding.inflate(layoutInflater, container, false)

        setDisplay()
        setCollectors()

        return binding.root
    }

    private fun setDisplay() {
        favouriteQuoteAdapter = QuoteAdapterOutlined()
        binding.recyclerViewFavouriteQuotes.adapter = favouriteQuoteAdapter
    }

    private fun setCollectors() {
        lifecycleScope.launchWhenCreated {
            viewModel.favouriteQuotes.collect { state ->
                when (state) {
                    is FavouriteQuoteUiState.LoadedData -> {
                        displayFavouriteQuotes(state.pagedQuotes)

                    }
                    else -> {

                    }
                }

            }
        }

        lifecycleScope.launch {
            favouriteQuoteAdapter.loadStateFlow.collect { loadState ->
                displayLoadingStates(loadState)
            }
        }

    }

    private fun displayLoadingStates(loadState: CombinedLoadStates) {
        val isInitialLoadOrRefresh = loadState.source.refresh is LoadState.Loading
        binding.progressBar.isVisible = isInitialLoadOrRefresh

        if (loadState.refresh is LoadState.NotLoading &&
            loadState.prepend is LoadState.NotLoading &&
            loadState.append is LoadState.NotLoading &&
            favouriteQuoteAdapter.itemCount > 0
        ) {
            binding.buttonLoadMore.isVisible = true
            binding.textLabelNoFavourite.isVisible = false
        }

        if (loadState.refresh is LoadState.NotLoading &&
            loadState.prepend is LoadState.NotLoading &&
            loadState.append is LoadState.NotLoading &&
            favouriteQuoteAdapter.itemCount == 0
        ) {
            binding.buttonLoadMore.isVisible = false
            binding.textLabelNoFavourite.isVisible = true
        }
    }

    private suspend fun displayFavouriteQuotes(pagedQuotes: PagingData<QuotePresentation>) {
        favouriteQuoteAdapter.submitData(pagedQuotes)
    }

}