package com.randev.movieapp_kmm.android.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.randev.domain.model.movie_credits.CastModel
import com.randev.movieapp_kmm.android.composable.components.image.BaseImageView
import com.randev.movieapp_kmm.android.composable.components.space.VerticalSpacer
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme
import com.randev.movieapp_kmm.android.composable.components.card.BASE_URL_IMAGE

/**
 * @author Raihan Arman
 * @date 21/10/22
 */

@Composable
fun CastItem(
    modifier: Modifier = Modifier,
    cast: CastModel
) {
    Column(
        modifier = modifier
            .width(60.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            BaseImageView(
                modifier = Modifier
                    .fillMaxSize(),
                url = "$BASE_URL_IMAGE${cast.profilePath}"
            )
        }
        VerticalSpacer(height = 10.dp)
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = cast.name,
            style = MovieAppTheme.typography.medium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewCastItem() {
    MovieAppTheme {
        CastItem(cast = CastModel(
            id = 1,
            name = "Jesse Lingard",
            profilePath = "https://pbs.twimg.com/media/E_BHlStVUAMkEeO.jpg:large",
            castId = 0,
            character = "",
            creditId = "",
            originalName = "",
            popularity = 0.0,
            gender = 0
        )
        )
    }
}