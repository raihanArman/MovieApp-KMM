package com.randev.movieapp_kmm.android.presentation.more_upcoming

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.movieapp_kmm.android.composable.components.app_bar.AppBarCustom
import com.randev.movieapp_kmm.android.composable.components.progressCircular.ProgressCircularComponent
import com.randev.movieapp_kmm.android.presentation.home.components.MovieItem
import com.randev.movieapp_kmm.android.presentation.home.components.PopularSection
import com.randev.movieapp_kmm.android.presentation.home.components.UpcomingSection
import com.randev.movieapp_kmm.android.utils.items
import org.koin.androidx.compose.getViewModel

/**
 * @author Raihan Arman
 * @date 22/10/22
 */

@Composable
fun MoreUpcomingScreen(
    viewModel: MoreUpcomingViewModel = getViewModel()
) {
    val upcomingData = viewModel.upcomingPagination.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            AppBarCustom(
                title = "Upcoming",
                onBackPressed = viewModel::onBackButtonClicked
            )
        },
        content = {
            ContentUpcoming(
                data = upcomingData,
                onClickMovie = viewModel::onNavigateToDetailsClicked
            )
        }
    )
}

@Composable
fun ContentUpcoming(
    modifier: Modifier = Modifier,
    data: LazyPagingItems<DataMovieModel>,
    onClickMovie: (Int) -> Unit
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
            }
        }
    }
}
