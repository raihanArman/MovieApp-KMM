package com.randev.movieapp_kmm.android.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.movieapp_kmm.android.presentation.home.components.MovieItem
import com.randev.movieapp_kmm.android.presentation.home.components.PopularSection
import com.randev.movieapp_kmm.android.presentation.home.components.UpcomingSection
import com.randev.movieapp_kmm.android.utils.items
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
) {
    val viewModel: HomeViewModel = getViewModel()

//    val lazyMovieList = viewModel.moviesPagination.collectAsLazyPagingItems()

    val observeHomeState by viewModel.observeMovieState.collectAsState()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is HomeViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
//            if(mainState.isLoading) {
//                ShowProgressCircular()
//            } else {
//                ContentMovie(data = mainState.movieList)
//            }
            ContentMovie(
                state = observeHomeState,
                onClickMovie = {
                    viewModel.onNavigateToDetailsClicked(it)
                },
                onClickMorePopular = {},
                onClickMoreUpcoming = {}
            )
        }
    }
}



@Composable
fun ContentMovie(
    modifier: Modifier = Modifier,
    state: HomeState,
    onClickMovie: (Int) -> Unit,
    onClickMoreUpcoming: () -> Unit,
    onClickMorePopular: () -> Unit
) {

    LazyColumn(
        modifier = modifier
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        state.upcomingList?.let {
            item {
                UpcomingSection(
                    data = it,
                    onClickMore = onClickMoreUpcoming,
                    onClickMovie = {
                        onClickMovie(it)
                    }
                )
            }
        }

        state.movieList?.let {
            item {
                PopularSection(
                    data = it,
                    onClickMore = onClickMorePopular,
                    onClickMovie = {
                        onClickMovie(it)
                    }
                )
            }
        }

        if(state.isLoading) {
            item {
                CircularProgressIndicator()
            }
        }

    }

//    LazyVerticalGrid(
//        modifier = modifier
//            .padding(25.dp),
//        columns = GridCells.Fixed(2),
//        verticalArrangement = Arrangement.spacedBy(10.dp),
//        horizontalArrangement = Arrangement.spacedBy(10.dp)
//    ) {
//        items(data) { movie ->
//            movie?.let {
//                MovieItem(data = it) {
//                    onClick(movie.id)
//                }
//            }
//        }
//    }
}