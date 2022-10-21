package com.randev.movieapp_kmm.android.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.movieapp_kmm.android.composable.components.image.BaseImageView
import com.randev.movieapp_kmm.android.composable.components.space.HorizontalSpacer
import com.randev.movieapp_kmm.android.composable.components.space.VerticalSpacer
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme
import com.randev.movieapp_kmm.android.composable.style.Shapes

/**
 * @author Raihan Arman
 * @date 21/10/22
 */

@Composable
fun PopularItem(
    modifier: Modifier = Modifier,
    movie: DataMovieModel,
    onClickMovie: (DataMovieModel) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .clickable {
                onClickMovie(movie)
            }
    ) {
        Card(
            modifier = Modifier
                .width(80.dp)
                .height(130.dp),
            elevation = 0.dp,
            shape = Shapes.medium
        ) {
            BaseImageView(
                url = "$BASE_URL_IMAGE${movie.posterPath}",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        HorizontalSpacer(width = 20.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = movie.originalTitle,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Black,
                style = MovieAppTheme.typography.bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MovieAppTheme.colors.colorAccent
                )
                HorizontalSpacer(width = 5.dp)
                Text(
                    text = "${movie.voteAverage}",
                    style = MovieAppTheme.typography.medium,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            VerticalSpacer(height = 10.dp)
            Text(
                text = "Release date : ${movie.releaseDate}",
                style = MovieAppTheme.typography.normal,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
    }
}