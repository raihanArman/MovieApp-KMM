package com.randev.movieapp_kmm.android.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randev.core.wrapper.Resource
import com.randev.domain.model.movie_credits.MovieCreditsModel
import com.randev.domain.model.movie_detail.MovieDetailModel
import com.randev.domain.usecase.GetMovieCreditsUseCase
import com.randev.domain.usecase.GetMovieDetailUseCase
import com.randev.movieapp_kmm.android.presentation.home.HomeViewModel
import com.randev.navigation.AppNavigator
import com.randev.navigation.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author Raihan Arman
 * @date 19/10/22
 */

class DetailViewModel(
    private val appNavigator: AppNavigator,
    private val stateHandle: SavedStateHandle,
    private val detailUseCase: GetMovieDetailUseCase,
    private val creditsUseCase: GetMovieCreditsUseCase
): ViewModel() {

    private var movieId: String ?= null

    private val _observeDetailState: MutableStateFlow<DetailState> = MutableStateFlow(DetailState())
    val observeDetailState: StateFlow<DetailState> = _observeDetailState

    init {
        movieId = stateHandle.get<String>(Destination.DetailsScreen.MOVIE_ID_KEY) ?: ""
        movieId?.let {
            getMovieDetail(it.toInt())
        }
    }

    private fun getMovieDetail(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                detailUseCase.invoke(movieId).onStart { emit(Resource.Idle()) },
                creditsUseCase.invoke(movieId).onStart { emit(Resource.Idle()) },
                ::Pair
            ).collect { detailState ->
                when(detailState.first) {
                    is Resource.Error -> {
                        _observeDetailState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = (detailState.first as Resource.Error).errorMessage
                            )
                        }
                    }
                    is Resource.Success -> {
                        _observeDetailState.update {
                            it.copy(
                                movieDetail = (detailState.first as Resource.Success<MovieDetailModel>).model,
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _observeDetailState.update {
                            it.copy(
                                isLoading = true,
                            )
                        }
                    }
                    else -> {}
                }

                when(detailState.second) {
                    is Resource.Error -> {
                        _observeDetailState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = (detailState.second as Resource.Error).errorMessage
                            )
                        }
                    }
                    is Resource.Success -> {
                        _observeDetailState.update {
                            it.copy(
                                castList = (detailState.second as Resource.Success<MovieCreditsModel>).model?.castList,
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _observeDetailState.update {
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

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack()
    }

}