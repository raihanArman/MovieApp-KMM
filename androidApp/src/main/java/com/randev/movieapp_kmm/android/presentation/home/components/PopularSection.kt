package com.randev.movieapp_kmm.android.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.movieapp_kmm.android.composable.components.card.PopularItem
import com.randev.movieapp_kmm.android.composable.components.space.VerticalSpacer

/**
 * @author Raihan Arman
 * @date 21/10/22
 */

@Composable
fun PopularSection(
    modifier: Modifier = Modifier,
    data: List<DataMovieModel>,
    onClickMore: () -> Unit,
    onClickMovie: (Int) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        HeaderItem(
            title = "Popular",
            onClickMore = onClickMore
        )
        VerticalSpacer(height = 10.dp)
        data.forEach { it ->
            PopularItem(
                movie = it,
                onClickMovie = {
                    onClickMovie(it.id)
                }
            )
        }
    }
}