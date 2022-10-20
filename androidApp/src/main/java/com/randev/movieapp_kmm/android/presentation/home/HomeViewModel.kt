package com.randev.movieapp_kmm.android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.randev.core.wrapper.Resource
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.domain.usecase.GetMovieUseCase
import com.randev.navigation.AppNavigator
import com.randev.navigation.Destination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

class HomeViewModel(
    private val appNavigator: AppNavigator,
    private val useCase: GetMovieUseCase,
): ViewModel() {
    private val _observeMovieState = MutableStateFlow(HomeState())
    val observeMovieState: StateFlow<HomeState> = _observeMovieState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val moviesPagination: Flow<PagingData<DataMovieModel>> = Pager(PagingConfig(pageSize = 20)) {
        HomeDataSource(useCase)
    }.flow

    fun onNavigateToDetailsClicked(id: Int) {
        appNavigator.tryNavigateTo(
            Destination.DetailsScreen(
                movieId = id
            )
        )
    }

    private fun getMovie(){
        viewModelScope.launch {
            useCase.invoke().collect { viewState ->
                when(viewState) {
                    is Resource.Error -> {
                        _observeMovieState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = viewState.errorMessage
                            )
                        }
                        _eventFlow.emit(
                            UIEvent.ShowSnackbar(
                                viewState.errorMessage ?: "Unknown error"
                            )
                        )
                    }
                    is Resource.Success -> {
                        _observeMovieState.update {
                            it.copy(
                                movieList = viewState.model?.results ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _observeMovieState.update {
                            it.copy(
                                isLoading = true,
                            )
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    sealed class UIEvent{
        data class ShowSnackbar(val message: String): UIEvent()
    }
}