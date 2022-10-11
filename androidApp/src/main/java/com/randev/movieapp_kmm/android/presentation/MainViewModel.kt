package com.randev.movieapp_kmm.android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randev.core.wrapper.Resource
import com.randev.domain.model.MovieModel
import com.randev.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

class MainViewModel(
    private val useCase: GetMovieUseCase
): ViewModel() {
    private val _observeMovie = MutableStateFlow<Resource<MovieModel>>(Resource.Idle())
    val observeMovie: StateFlow<Resource<MovieModel>> = _observeMovie

    init {
        getMovie()
    }

    fun getMovie(){
        viewModelScope.launch {
            useCase.invoke().collect { viewState ->
                _observeMovie.value = viewState
            }
        }
    }
}