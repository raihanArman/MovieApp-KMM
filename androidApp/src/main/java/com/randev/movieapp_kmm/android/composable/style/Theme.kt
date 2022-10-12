package com.randev.movieapp_kmm.android.composable.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * @author Raihan Arman
 * @date 12/10/22
 */

@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val movieComposeColors = if (darkTheme) MovieComposeColorsDark() else MovieComposeColorsLight()
    val movieComposeTypography = MovieComposeTypography()
    
    CompositionLocalProvider(
        LocalMovieComposeColors provides movieComposeColors,
        LocalMovieComposeTypography provides movieComposeTypography
    ) {
        MaterialTheme(
            content = content
        )
    }
}

object MovieAppTheme {
    val colors: BaseMovieComposeColors
        @Composable
        get() = LocalMovieComposeColors.current

    val typography: BaseMovieComposeTypography
        @Composable
        get() = LocalMovieComposeTypography.current
}