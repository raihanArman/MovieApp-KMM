package com.randev.movieapp_kmm.android.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.movieapp_kmm.android.composable.components.card.BASE_URL_IMAGE
import com.randev.movieapp_kmm.android.composable.components.image.BaseImageView
import com.randev.movieapp_kmm.android.composable.components.space.VerticalSpacer
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme
import com.randev.movieapp_kmm.android.composable.style.Shapes

/**
 * @author Raihan Arman
 * @date 21/10/22
 */

@Composable
fun UpcomingItem(
    modifier: Modifier = Modifier,
    movie: DataMovieModel,
    onClickMovie: (DataMovieModel) -> Unit
) {
    Column(
        modifier = modifier
            .width(100.dp)
            .clickable {
                onClickMovie(movie)
            }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            elevation = 0.dp,
            shape = Shapes.medium
        ) {
            BaseImageView(
                url = "$BASE_URL_IMAGE${movie.posterPath}",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        VerticalSpacer(height = 10.dp)
        Text(
            text = movie.originalTitle,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.Black,
            style = MovieAppTheme.typography.bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}