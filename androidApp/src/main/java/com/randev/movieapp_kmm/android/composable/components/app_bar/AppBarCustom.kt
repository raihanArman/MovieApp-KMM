package com.randev.movieapp_kmm.android.composable.components.app_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme

/**
 * @author Raihan Arman
 * @date 22/10/22
 */

@Composable
fun AppBarCustom(
    modifier: Modifier = Modifier,
    title: String,
    onBackPressed: (() -> Unit?)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                color = Color.Black,
                style = MovieAppTheme.typography.bold,
                fontSize = 18.sp
            )
        },
        backgroundColor = MovieAppTheme.colors.colorAccent,
        navigationIcon = onBackPressed?.let {
            {
                IconButton(
                    onClick = {
                        it.invoke()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        actions = actions
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAppBarCustom() {
    MovieAppTheme() {
        AppBarCustom(title = "Upcoming", onBackPressed = {})
    }
}