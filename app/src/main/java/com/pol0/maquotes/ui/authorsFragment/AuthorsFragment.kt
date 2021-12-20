package com.pol0.maquotes.ui.authorsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.pol0.maquotes.adapters.AuthorAdapter
import com.pol0.maquotes.databinding.AuthorsFragmentBinding
import com.pol0.maquotes.model.AuthorPresentation
import com.pol0.maquotes.ui.authorsFragment.AuthorsViewModel.AuthorsUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class AuthorsFragment : Fragment() {

    private val viewModel: AuthorsViewModel by activityViewModels()

    private lateinit var binding: AuthorsFragmentBinding
    private lateinit var recommendedAuthorsAdapter: AuthorAdapter
    private lateinit var authorsAdapter: AuthorAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthorsFragmentBinding.inflate(layoutInflater, container, false)

        setDisplay()
        setCollectors()

        return binding.root
    }


    private fun setDisplay() {
        recommendedAuthorsAdapter = AuthorAdapter(AuthorAdapter.OnClickListener { author ->
            navigateToAuthorDetails(author)
        })
        authorsAdapter = AuthorAdapter(AuthorAdapter.OnClickListener { author ->
            navigateToAuthorDetails(author)
        })

        binding.recyclerViewRecommendedAuthors.adapter = recommendedAuthorsAdapter
        binding.recyclerViewAuthors.adapter = authorsAdapter
    }

    private fun navigateToAuthorDetails(author: AuthorPresentation) {
        try {
            val action = AuthorsFragmentDirections.actionAuthorsFragmentToAuthorDetailsFragment(author)
            findNavController().navigate(action)
        } catch (exception: Exception) {
            Timber.e(exception)
        }
    }

    private fun setCollectors() {

        lifecycleScope.launchWhenCreated {
            viewModel.recommendedAuthors.collect { state ->
                when (state) {
                    is AuthorsUiState.LoadedData -> {
                        displayRecommendedAuthors(state.pagedAuthors)

                    }
                    else -> {

                    }
                }

            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.authors.collect { state ->
                when (state) {
                    is AuthorsUiState.LoadedData -> {
                        displayAuthors(state.pagedAuthors)
                    }
                    else -> {

                    }
                }

            }
        }


        lifecycleScope.launch {
            recommendedAuthorsAdapter.loadStateFlow.collect { loadState ->
                displayRecommendedAuthorsStatesAuthors(loadState)
            }
        }

        lifecycleScope.launch {
            authorsAdapter.loadStateFlow.collect { loadState ->
                displayAuthorsStates(loadState)
            }
        }

    }

    private fun displayRecommendedAuthorsStatesAuthors(loadState: CombinedLoadStates) {
        val isInitialLoadOrRefresh = loadState.source.refresh is LoadState.Loading
        binding.progressRecommendedAuthors.isVisible = isInitialLoadOrRefresh

        /*if (loadState.refresh is LoadState.NotLoading &&
            loadState.prepend is LoadState.NotLoading &&
            loadState.append is LoadState.NotLoading &&
            recommendedAuthorsAdapter.itemCount > 0) {
            binding.textLabelRecommendedAuthors.isVisible = true
        }*/

        if (recommendedAuthorsAdapter.itemCount > 0) {
            binding.textLabelRecommendedAuthors.isVisible = true
        }
    }

    private fun displayAuthorsStates(loadState: CombinedLoadStates) {
        val isInitialLoadOrRefresh = loadState.source.refresh is LoadState.Loading
        binding.progressAuthors.isVisible = isInitialLoadOrRefresh

        /*if (loadState.refresh is LoadState.NotLoading &&
            loadState.prepend is LoadState.NotLoading &&
            loadState.append is LoadState.NotLoading &&
            recommendedAuthorsAdapter.itemCount > 0) {
            binding.textLabelAuthors.isVisible = true
        }*/
        if (authorsAdapter.itemCount > 0) {
            binding.textLabelAuthors.isVisible = true
        }
    }


    private suspend fun displayRecommendedAuthors(pagedAuthors: PagingData<AuthorPresentation>) {
        recommendedAuthorsAdapter.submitData(pagedAuthors)
    }

    private suspend fun displayAuthors(pagedAuthors: PagingData<AuthorPresentation>) {
        authorsAdapter.submitData(pagedAuthors)
    }

}