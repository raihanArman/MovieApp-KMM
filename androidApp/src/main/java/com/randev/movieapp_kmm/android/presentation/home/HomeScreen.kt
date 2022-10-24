package com.randev.movieapp_kmm.android.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.randev.movieapp_kmm.android.composable.components.app_bar.AppBarCustom
import com.randev.movieapp_kmm.android.presentation.home.components.PopularSection
import com.randev.movieapp_kmm.android.presentation.home.components.UpcomingSection
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
        scaffoldState = scaffoldState,
        topBar = {
            AppBarCustom(
                title = "Movie App",
                actions = {
                    IconAction(
                        icon = Icons.Default.FavoriteBorder,
                        onClickSearch = viewModel::onNavigateToFavorite
                    )
                    IconAction(
                        icon = Icons.Default.Search,
                        onClickSearch = viewModel::onNavigateToSearch
                    )
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            ContentMovie(
                state = observeHomeState,
                onClickMovie = viewModel::onNavigateToDetailsClicked,
                onClickMorePopular = viewModel::onNavigateToMorePopular,
                onClickMoreUpcoming = viewModel::onNavigateToMoreUpcoming
            )
        }
    }
}

@Composable
fun IconAction(
    modifier: Modifier = Modifier,
    onClickSearch: () -> Unit,
    icon: ImageVector
) {
    IconButton(
        onClick = onClickSearch,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black
        )
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
}