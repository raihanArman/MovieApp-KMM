package com.randev.movieapp_kmm.android.composable.style

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.sp
import com.randev.movieapp_kmm.android.R

/**
 * @author Raihan Arman
 * @date 12/10/22
 */

object MovieComposeFonts {
    val regular : Font = Font(R.font.sf_pro_text_regular)
    val bold : Font = Font(R.font.sf_pro_text_bold)
    val medium : Font = Font(R.font.sf_pro_text_medium)
    val light : Font = Font(R.font.sf_pro_text_light)
}

@Immutable
data class MovieComposeTypography(
    override val medium: TextStyle = TextStyle(
        fontFamily = MovieComposeFonts.medium.toFontFamily(),
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = Colors.white
    ),
    override val bold: TextStyle = TextStyle(
        fontFamily = MovieComposeFonts.bold.toFontFamily(),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = Colors.white
    ),
    override val light: TextStyle = TextStyle(
        fontFamily = MovieComposeFonts.light.toFontFamily(),
        fontSize = 14.sp,
        fontWeight = FontWeight.Light,
        color = Colors.white
    ),
    override val normal: TextStyle = TextStyle(
        fontFamily = MovieComposeFonts.regular.toFontFamily(),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = Colors.white
    )
): BaseMovieComposeTypography()