package com.randev.movieapp_kmm.android.composable.components.favorite

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.randev.domain.model.movie_detail.MovieDetailModel
import kotlinx.coroutines.launch

/**
 * @author Raihan Arman
 * @date 24/10/22
 */

@Composable
fun FavoriteIcon(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    data: MovieDetailModel,
    onClickFavorite: (MovieDetailModel) -> Unit
) {
    val scale = remember { Animatable(initialValue = 1f) }

    LaunchedEffect(key1 = isFavorite) {
        if(isFavorite) {
            scale.animateTo(
                targetValue = 0.3f,
                animationSpec = tween(
                    durationMillis = 50
                )
            )
            scale.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }
    }

    IconButton(
        modifier = modifier
            .padding(0.dp),
        onClick = {
            onClickFavorite(data)
        }
    ) {
        Icon(
            modifier = Modifier
                .scale(scale.value),
            imageVector = if(isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder ,
            contentDescription = null,
            tint = Color.Red
        )
    }

}