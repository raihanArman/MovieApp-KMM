package com.randev.movieapp_kmm.android.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.movieapp_kmm.android.composable.components.space.VerticalSpacer

/**
 * @author Raihan Arman
 * @date 21/10/22
 */

@Composable
fun UpcomingSection(
    modifier: Modifier =  Modifier,
    data: List<DataMovieModel>,
    onClickMore: () -> Unit,
    onClickMovie: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        HeaderItem(
            title = "Upcoming",
            onClickMore = onClickMore
        )
        VerticalSpacer(height = 10.dp)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(data) { it ->
                UpcomingItem(
                    movie = it,
                    onClickMovie = {
                        onClickMovie(it.id)
                    }
                )
            }
        }
    }
}