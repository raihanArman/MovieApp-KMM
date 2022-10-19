package com.randev.movieapp_kmm.android.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.randev.core.wrapper.Resource
import com.randev.domain.model.DataMovieModel
import com.randev.domain.model.MovieModel
import com.randev.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.Dispatchers
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

class MainViewModel(
    private val useCase: GetMovieUseCase
): ViewModel() {
    private val _observeMovieState = MutableStateFlow(MainState())
    val observeMovieState: StateFlow<MainState> = _observeMovieState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val moviesPagination: Flow<PagingData<DataMovieModel>> = Pager(PagingConfig(pageSize = 20)) {
        MainDataSource(useCase)
    }.flow

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