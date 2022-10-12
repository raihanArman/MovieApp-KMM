package com.randev.movieapp_kmm.android.presentation.main.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.randev.domain.model.DataMovieModel
import com.randev.movieapp_kmm.android.R
import com.randev.movieapp_kmm.android.composable.components.BaseImageView
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme
import com.randev.movieapp_kmm.android.composable.style.Shapes

/**
 * @author Raihan Arman
 * @date 12/10/22
 */

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    data: DataMovieModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        elevation = 0.dp,
        shape = Shapes.large,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            BaseImageView(
                modifier = Modifier.fillMaxSize(),
                url = data.posterPath
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Black.copy(alpha = 0.8f),
            ) {
                Text(
                    text = data.originalTitle,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    color = Color.White,
                    style = MovieAppTheme.typography.bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMovieItem() {
//    MovieItem()
}