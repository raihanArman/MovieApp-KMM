package com.randev.movieapp_kmm.android.composable.style

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

/**
 * @author Raihan Arman
 * @date 12/10/22
 */

abstract class BaseMovieComposeColors {
    abstract val colorPrimary: Color
    abstract val colorAccent: Color
    abstract val colorPrimaryDark: Color
    abstract val colorTextPrimary: Color
    abstract val colorTextSecondary: Color
}

val LocalMovieComposeColors = staticCompositionLocalOf<BaseMovieComposeColors> {
    MovieComposeColorsDark()
    MovieComposeColorsLight()
}

abstract class BaseMovieComposeTypography {
    abstract val medium: TextStyle
    abstract val bold: TextStyle
    abstract val light: TextStyle
    abstract val normal: TextStyle
}

val LocalMovieComposeTypography = staticCompositionLocalOf<BaseMovieComposeTypography> {
    MovieComposeTypography()
}