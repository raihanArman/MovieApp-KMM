package com.randev.movieapp_kmm.android.composable.style

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.randev.movieapp_kmm.android.R
import com.randev.movieapp_kmm.android.composable.utils.asDisabledColor

/**
 * @author Raihan Arman
 * @date 12/10/22
 */

object Colors {
    val white: Color = Color(0xFFFFFFFF)
    val black: Color = Color(0xFF000000)
    val raisinBlack: Color = Color(0xFF202123)
    val charlestonGreen: Color = Color(0xFF25272A)
    val sunGlow: Color = Color(0xFFFFD130)
    val transparent: Color = Color(0xFF00000000)
    val red: Color = Color.Red
}

@Immutable
data class MovieComposeColorsDark(
    override val colorPrimary: Color = Colors.charlestonGreen,
    override val colorAccent: Color = Colors.sunGlow,
    override val colorPrimaryDark: Color = Colors.black,
    override val colorTextPrimary: Color = Colors.white,
    override val colorTextSecondary: Color = Colors.white.asDisabledColor()
) : BaseMovieComposeColors()


@Immutable
data class MovieComposeColorsLight(
    override val colorPrimary: Color = Colors.charlestonGreen,
    override val colorAccent: Color = Colors.sunGlow,
    override val colorPrimaryDark: Color = Colors.black,
    override val colorTextPrimary: Color = Colors.white,
    override val colorTextSecondary: Color = Colors.white.asDisabledColor()
) : BaseMovieComposeColors()