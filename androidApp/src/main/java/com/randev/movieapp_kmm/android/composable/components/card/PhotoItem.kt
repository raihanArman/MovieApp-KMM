package com.randev.movieapp_kmm.android.composable.components.card

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.randev.movieapp_kmm.android.composable.components.image.BaseImageView
import com.randev.movieapp_kmm.android.composable.style.Shapes

/**
 * @author Raihan Arman
 * @date 24/10/22
 */

@Composable
fun PhotoItem(
    modifier: Modifier = Modifier
        .width(220.dp)
        .height(100.dp),
    url: String
) {
    Card(
        modifier = modifier,
        elevation = 0.dp,
        shape = Shapes.medium
    ) {
        BaseImageView(
            modifier = Modifier
                .fillMaxSize(),
            url = BASE_URL_IMAGE + url
        )
    }
}