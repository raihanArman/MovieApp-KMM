package com.randev.movieapp_kmm.android.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.movieapp_kmm.android.composable.components.app_bar.SearchAppBar
import com.randev.movieapp_kmm.android.composable.components.progressCircular.ProgressCircularComponent
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme
import com.randev.movieapp_kmm.android.presentation.home.components.MovieItem
import com.randev.movieapp_kmm.android.utils.items
import org.koin.androidx.compose.getViewModel

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = getViewModel()
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchData = viewModel.searchFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchAppBar(
                text = { searchQuery },
                onTextChange = {
                    viewModel.updateSearchQuery(it)
                },
                onSearchClicked = {
                    viewModel.updateSearchQuery(it)
                },
                onCloseClicked = {
                    viewModel.onBackButtonClicked()
                }
            )
        },
        content = {
            ContentSearch(
                data = searchData,
                onClickMovie = viewModel::onNavigateToDetailsClicked,
                query = searchQuery
            )
        }
    )
}


@Composable
fun ContentSearch(
    modifier: Modifier = Modifier,
    data: LazyPagingItems<DataMovieModel>,
    onClickMovie: (Int) -> Unit,
    query: String
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
                    onClickMovie(movie.id)
                }
            }
        }


        data.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item(span = { GridItemSpan(2) }) {
                        ProgressCircularComponent(modifier = Modifier.fillMaxWidth())
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item(span = { GridItemSpan(2) }) {
                        ProgressCircularComponent(modifier = Modifier.fillMaxWidth())
                    }
                }
//                loadState.append is LoadState.NotLoading -> {
//                    if(loadState.append.endOfPaginationReached && data.itemCount < 1 && query.isNotEmpty()) {
//                        item(span = { GridItemSpan(2) }) {
//                            EmptyScreen(query = query)
//                        }
//                    }
//                }
            }
        }
    }
}

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    query: String
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Result \"$query\" is empty",
            color = Color.Gray,
            style = MovieAppTheme.typography.light,
            fontSize = 18.sp
        )
    }
}