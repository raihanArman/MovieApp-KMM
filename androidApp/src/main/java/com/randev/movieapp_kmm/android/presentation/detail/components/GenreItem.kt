package com.randev.movieapp_kmm.android.presentation.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.randev.domain.model.movie_detail.GenreModel
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme

/**
 * @author Raihan Arman
 * @date 21/10/22
 */

@Composable
fun GenreItem(
    modifier: Modifier = Modifier,
    genre: GenreModel
) {
    Box(
        modifier = modifier
    ){
        Surface(
            modifier = Modifier
                .fillMaxHeight(),
            shape = CircleShape,
            color = MovieAppTheme.colors.colorAccent
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 3.dp),
                text = genre.name,
                style = MovieAppTheme.typography.medium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}