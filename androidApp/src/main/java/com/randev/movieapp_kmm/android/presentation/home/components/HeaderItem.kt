package com.randev.movieapp_kmm.android.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme

/**
 * @author Raihan Arman
 * @date 21/10/22
 */

@Composable
fun HeaderItem(
    modifier: Modifier = Modifier,
    title: String,
    onClickMore: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = title,
            color = Color.Black,
            style = MovieAppTheme.typography.bold,
            fontSize = 24.sp
        )
        TextButton(
            onClick = onClickMore
        ) {
            Text(
                text = "See more",
                color = MovieAppTheme.colors.colorAccent,
                style = MovieAppTheme.typography.medium,
                fontSize = 16.sp
            )
        }
    }
}