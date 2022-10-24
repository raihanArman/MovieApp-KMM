package com.randev.movieapp_kmm.android.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randev.core.wrapper.Resource
import com.randev.domain.model.favorite.FavoriteModel
import com.randev.domain.model.movie_list.MovieModel
import com.randev.domain.usecase.GetFavoriteUseCase
import com.randev.navigation.AppNavigator
import com.randev.navigation.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

class FavoriteViewModel(
    private val useCase: GetFavoriteUseCase,
    private val appNavigator: AppNavigator
): ViewModel() {

    private val _observeFavoriteState = MutableStateFlow(FavoriteState())
    val observeFavoriteState = _observeFavoriteState

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

    fun getFavorite() {
        viewModelScope.launch {
            useCase.invoke().collect { resource ->
                when(resource) {
                    is Resource.Error -> {
                        _observeFavoriteState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = (resource as Resource.Error).errorMessage
                            )
                        }
                    }
                    is Resource.Success -> {
                        _observeFavoriteState.update {
                            it.copy(
                                favoriteList = resource.model,
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _observeFavoriteState.update {
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

}