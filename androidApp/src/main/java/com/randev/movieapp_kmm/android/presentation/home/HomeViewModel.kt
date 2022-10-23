package com.randev.movieapp_kmm.android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.randev.core.wrapper.Resource
import com.randev.domain.model.movie_detail.MovieDetailModel
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.domain.model.movie_list.MovieModel
import com.randev.domain.usecase.GetMovieUpcomingUseCase
import com.randev.domain.usecase.GetMovieUseCase
import com.randev.navigation.AppNavigator
import com.randev.navigation.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

class HomeViewModel(
    private val appNavigator: AppNavigator,
    private val movieUseCase: GetMovieUseCase,
    private val upcomingUseCase: GetMovieUpcomingUseCase
): ViewModel() {
    private val _observeMovieState = MutableStateFlow(HomeState())
    val observeMovieState: StateFlow<HomeState> = _observeMovieState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getMovie()
    }

    fun onNavigateToDetailsClicked(id: Int) {
        appNavigator.tryNavigateTo(
            Destination.DetailsScreen(
                movieId = id
            )
        )
    }

    fun onNavigateToMoreUpcoming() {
        appNavigator.tryNavigateTo(Destination.MoreUpcomingScreen.fullRoute)
    }

    fun onNavigateToMorePopular() {
        appNavigator.tryNavigateTo(Destination.MorePopularScreen.fullRoute)
    }

    fun onNavigateToSearch() {
        appNavigator.tryNavigateTo(Destination.SearchScreen.fullRoute)
    }

    private fun getMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                movieUseCase.invoke(1).onStart { emit(Resource.Idle()) },
                upcomingUseCase.invoke(1).onStart { emit(Resource.Idle()) },
                ::Pair
            ).collect { movieState ->
                when(movieState.first) {
                    is Resource.Error -> {
                        _observeMovieState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = (movieState.first as Resource.Error).errorMessage
                            )
                        }
                    }
                    is Resource.Success -> {
                        _observeMovieState.update {
                            it.copy(
                                movieList = (movieState.first as Resource.Success<MovieModel>).model?.results,
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

                when(movieState.second) {
                    is Resource.Error -> {
                        _observeMovieState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = (movieState.second as Resource.Error).errorMessage
                            )
                        }
                    }
                    is Resource.Success -> {
                        _observeMovieState.update {
                            it.copy(
                                upcomingList = (movieState.second as Resource.Success<MovieModel>).model?.results,
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