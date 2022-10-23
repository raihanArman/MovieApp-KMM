package com.randev.movieapp_kmm.android.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.domain.usecase.SearchMovieUseCase
import com.randev.navigation.AppNavigator
import com.randev.navigation.Destination
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModel(
    private val useCase: SearchMovieUseCase,
    private val appNavigator: AppNavigator
): ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery

    val searchFlow = _searchQuery.filter {
        it.isNotEmpty()
    }.flatMapLatest {
        delay(1000) //Debounce
        Log.d("SearchViewodel", ": query -> $it")
        getPagination(query = it)
    }

    private fun getPagination(query: String): Flow<PagingData<DataMovieModel>> {
        return Pager(PagingConfig(pageSize = 20)) {
            SearchPagingSource(useCase, query)
        }.flow.cachedIn(viewModelScope)
    }

    fun updateSearchQuery(query: String){
        _searchQuery.value = query
    }

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack()
    }

    fun onNavigateToDetailsClicked(id: Int) {
        appNavigator.tryNavigateTo(
            Destination.DetailsScreen(
                movieId = id
            )
        )
    }
}