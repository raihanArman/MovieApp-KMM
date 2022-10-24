package com.randev.movieapp_kmm.android.presentation.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.randev.movieapp_kmm.android.composable.components.app_bar.AppBarCustom
import com.randev.movieapp_kmm.android.composable.components.progressCircular.ProgressCircularComponent
import com.randev.movieapp_kmm.android.composable.components.card.MovieItem
import com.randev.movieapp_kmm.android.composable.components.error.ErrorText
import org.koin.androidx.compose.getViewModel

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = getViewModel()
) {
    val state by viewModel.observeFavoriteState.collectAsState()

    LaunchedEffect(key1 = state) {
        viewModel.getFavorite()
    }

    Scaffold(
        topBar = {
            AppBarCustom(
                title = "Favorite",
                onBackPressed = viewModel::onBackButtonClicked
            )
        },
        content = {
            ContentFavorite(
                state = state,
                onClickFavorite = viewModel::onNavigateToDetailsClicked
            )
        }
    )
}

@Composable
fun ContentFavorite(
    modifier: Modifier = Modifier,
    state: FavoriteState,
    onClickFavorite: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier
            .padding(25.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        state.favoriteList?.let { data ->
            if(data.isNotEmpty()) {
                items(data) { movie ->
                    MovieItem(
                        id = { movie.id },
                        image = { movie.poster },
                        title = { movie.title }
                    ) {
                        onClickFavorite(movie.id)
                    }
                }
            } else {
                item(span = { GridItemSpan(2) }) {
                    ErrorText(
                        modifier = Modifier.fillMaxSize(),
                        message = "Favorite is empty"
                    )
                }
            }
        }

        if(state.isLoading) {
            item(span = { GridItemSpan(2) }) {
                ProgressCircularComponent(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}


