package com.randev.movieapp_kmm.android.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.movieapp_kmm.android.presentation.home.components.MovieItem
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

    val lazyMovieList = viewModel.moviesPagination.collectAsLazyPagingItems()
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
            contentAlignment = Alignment.Center
        ) {
//            if(mainState.isLoading) {
//                ShowProgressCircular()
//            } else {
//                ContentMovie(data = mainState.movieList)
//            }
            ContentMovie(data = lazyMovieList) {
                viewModel.onNavigateToDetailsClicked(it)
            }
        }
    }
}



@Composable
fun ContentMovie(
    modifier: Modifier = Modifier,
    data: LazyPagingItems<DataMovieModel>,
    onClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier
            .padding(25.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(data) { movie ->
            movie?.let {
                MovieItem(data = it) {
                    onClick(movie.id)
                }
            }
        }
    }
}