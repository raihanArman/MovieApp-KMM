package com.randev.movieapp_kmm.android.presentation.more_upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.domain.usecase.GetMovieUpcomingUseCase
import com.randev.navigation.AppNavigator
import com.randev.navigation.Destination
import kotlinx.coroutines.flow.Flow

/**
 * @author Raihan Arman
 * @date 22/10/22
 */

class MoreUpcomingViewModel(
    private val useCase: GetMovieUpcomingUseCase,
    private val appNavigator: AppNavigator
): ViewModel() {

    val upcomingPagination: Flow<PagingData<DataMovieModel>> = Pager(PagingConfig(pageSize = 20)) {
        MoreUpcomingPagingSource(useCase)
    }.flow.cachedIn(viewModelScope)

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