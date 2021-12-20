package com.pol0.maquotes.ui.authorDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.pol0.maquotes.adapters.QuoteAdapterOutlined
import com.pol0.maquotes.databinding.AuthorDetailsFragmentBinding
import com.pol0.maquotes.model.QuotePresentation
import com.pol0.maquotes.ui.favouritesFragment.FavouritesViewModel
import com.pol0.maquotes.ui.homeFragment.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthorDetailsFragment : Fragment() {

    private val viewModel: AuthorDetailsViewModel by viewModels()

    private lateinit var binding: AuthorDetailsFragmentBinding

    private lateinit var famousQuotesAdapter: QuoteAdapterOutlined

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthorDetailsFragmentBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setDisplayContent()
        setCollectors()

        return binding.root
    }

    private fun setCollectors() {
        lifecycleScope.launchWhenCreated {
            viewModel.famousQuotes.collect { state ->
                when (state) {
                    is HomeViewModel.QuoteUiState.LoadedData -> {
                        displayFamousQuotes(state.pagedQuotes)

                    }
                    else -> {

                    }
                }

            }
        }

        lifecycleScope.launch {
            famousQuotesAdapter.loadStateFlow.collect { loadState ->
                displayLoadingStates(loadState)
            }
        }
    }

    private fun displayLoadingStates(loadState: CombinedLoadStates) {
        val isInitialLoadOrRefresh = loadState.source.refresh is LoadState.Loading
        binding.progressFamousQuotes.isVisible = isInitialLoadOrRefresh

        if(famousQuotesAdapter.itemCount > 0){
            binding.textLabelFamousQuote.isVisible = true
            binding.textLabelNoQuotes.isVisible = false
            binding.buttonLoadMore.isVisible = true
        }else{
            binding.textLabelFamousQuote.isVisible = false
            binding.textLabelNoQuotes.isVisible = true
            binding.buttonLoadMore.isVisible = false
        }
    }

    private suspend fun displayFamousQuotes(pagedQuotes: PagingData<QuotePresentation>) {
        famousQuotesAdapter.submitData(pagedQuotes)
    }

    private fun setDisplayContent() {
        famousQuotesAdapter = QuoteAdapterOutlined()
        binding.recyclerViewFamousQuotes.adapter = famousQuotesAdapter
    }


}