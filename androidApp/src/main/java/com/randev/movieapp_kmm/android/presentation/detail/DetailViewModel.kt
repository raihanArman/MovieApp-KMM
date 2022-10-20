package com.randev.movieapp_kmm.android.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randev.core.wrapper.Resource
import com.randev.domain.model.movie_detail.MovieDetailModel
import com.randev.domain.usecase.GetMovieDetailUseCase
import com.randev.movieapp_kmm.android.presentation.home.HomeViewModel
import com.randev.navigation.AppNavigator
import com.randev.navigation.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author Raihan Arman
 * @date 19/10/22
 */

class DetailViewModel(
    private val appNavigator: AppNavigator,
    private val stateHandle: SavedStateHandle,
    private val useCase: GetMovieDetailUseCase
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
        viewModelScope.launch(Dispatchers.IO){
            useCase(movieId).collect { viewState ->
                when(viewState) {
                    is Resource.Error -> {
                        _observeDetailState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = viewState.errorMessage
                            )
                        }
                    }
                    is Resource.Success -> {
                        _observeDetailState.update {
                            it.copy(
                                movieDetail = viewState.model,
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