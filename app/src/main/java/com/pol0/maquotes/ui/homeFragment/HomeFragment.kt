package com.pol0.maquotes.ui.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.snackbar.Snackbar
import com.pol0.maquotes.adapters.AuthorAdapter
import com.pol0.maquotes.adapters.QuoteAdapter
import com.pol0.maquotes.adapters.QuoteAdapter.OnClickListener
import com.pol0.maquotes.databinding.HomeFragmentBinding
import com.pol0.maquotes.model.AuthorPresentation
import com.pol0.maquotes.model.QuotePresentation
import com.pol0.maquotes.ui.authorsFragment.AuthorsFragmentDirections
import com.pol0.maquotes.ui.authorsFragment.AuthorsViewModel
import com.pol0.maquotes.ui.authorsFragment.AuthorsViewModel.AuthorsUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.math.abs

@InternalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private val authorsViewModel: AuthorsViewModel by activityViewModels()

    private lateinit var binding: HomeFragmentBinding
    private lateinit var quoteAdapter: QuoteAdapter
    private lateinit var recommendedAuthorAdapter: AuthorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)

        setDisplay()
        setCollectors()

        return binding.root
    }

    private fun setDisplay() {
        /*binding.recyclerQuotes.adapter = quoteAdapter.withLoadStateHeaderAndFooter(
            header = QuoteLoadStateAdapter { quoteAdapter.retry() },
            footer = QuoteLoadStateAdapter { quoteAdapter.retry() },
        )*/

        quoteAdapter = QuoteAdapter(OnClickListener { quote ->
            addToFavourite(quote)
        })
        recommendedAuthorAdapter = AuthorAdapter(AuthorAdapter.OnClickListener{author->
            navigateToAuthorDetails(author)
        })

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position ->
                val radius = 1 - abs(position)
                page.scaleY = (0.85f + radius * 0.15f)
            }
        }

        with(binding.viewPagerSliderHome) {
            adapter = quoteAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            setPageTransformer(compositePageTransformer)
        }
        binding.recyclerViewRecommendedAuthors.adapter = recommendedAuthorAdapter
    }

    private fun navigateToAuthorDetails(author: AuthorPresentation) {
        try {
            val action = HomeFragmentDirections.actionHomeFragmentToAuthorDetailsFragment(author)
            findNavController().navigate(action)
        } catch (exception: Exception) {
            Timber.e(exception)
        }
    }

    private fun addToFavourite(quote: QuotePresentation) {
        lifecycleScope.launch {
            viewModel.addToFavourite(quote).collect { rows ->
                if (rows != -1L) {
                    Snackbar.make(
                        binding.root,
                        "Successfully added to favourites",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(binding.root, "Failed to add favourites", Snackbar.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }

    private fun setCollectors() {
        lifecycleScope.launchWhenCreated {
            viewModel.quotes.collect { state ->
                when (state) {
                    is HomeViewModel.QuoteUiState.LoadedData -> {
                        displayQuotes(state.pagedQuotes)

                    }
                    else -> {

                    }
                }

            }
        }

        lifecycleScope.launchWhenCreated {
            authorsViewModel.recommendedAuthors.collect { state ->
                when (state) {
                    is AuthorsUiState.LoadedData -> {
                        displayRecommendedAuthors(state.pagedAuthors)
                    }
                    else -> {

                    }
                }

            }
        }

        lifecycleScope.launch {
            quoteAdapter.loadStateFlow.collect { loadState ->
                displayLoadingStates(loadState)
            }
        }

        lifecycleScope.launch {
            recommendedAuthorAdapter.loadStateFlow.collect { loadState ->
                displayLoadingStatesAuthors(loadState)
            }
        }

    }

    private fun displayLoadingStatesAuthors(loadState: CombinedLoadStates) {
        val isInitialLoadOrRefresh = loadState.source.refresh is LoadState.Loading
        binding.progressRecommendedAuthors.isVisible = isInitialLoadOrRefresh

        /*if (loadState.refresh is LoadState.NotLoading &&
            loadState.prepend is LoadState.NotLoading &&
            loadState.append is LoadState.NotLoading &&
            recommendedAuthorAdapter.itemCount > 0) {
            binding.textLabelRecommendedAuthors.isVisible = true
        }*/
        if(recommendedAuthorAdapter.itemCount > 0){
            binding.textLabelRecommendedAuthors.isVisible = true
        }
    }

    private suspend fun displayRecommendedAuthors(pagedAuthors: PagingData<AuthorPresentation>) {
        recommendedAuthorAdapter.submitData(pagedAuthors)
    }

    private suspend fun displayQuotes(pagedQuotes: PagingData<QuotePresentation>) {
        quoteAdapter.submitData(pagedQuotes)
    }

    private fun displayLoadingStates(loadState: CombinedLoadStates) {
        val isInitialLoadOrRefresh = loadState.source.refresh is LoadState.Loading
        binding.progressBar.isVisible = isInitialLoadOrRefresh


        val isEmptyList = loadState.refresh is LoadState.NotLoading && quoteAdapter.itemCount == 0


        val initialLoadOrRefreshFail = loadState.source.refresh is LoadState.Error
        binding.buttonRetry.isVisible = initialLoadOrRefreshFail
        binding.buttonRetry.setOnClickListener {
            quoteAdapter.retry()
        }

        val errorOccurred = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
            ?: loadState.append as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error
        errorOccurred?.let {
            Snackbar.make(
                binding.root,
                "An Error has Occurred: ${it.error.message}",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

}