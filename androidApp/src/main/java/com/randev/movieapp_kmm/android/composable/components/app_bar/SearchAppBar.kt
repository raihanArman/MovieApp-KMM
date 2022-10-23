package com.randev.movieapp_kmm.android.composable.components.app_bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme

/**
 * @author Raihan Arman
 * @date 23/10/22
 */

@Composable
fun SearchAppBar(
    modifier: Modifier = Modifier,
    text: () -> String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MovieAppTheme.colors.colorAccent,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text.invoke(),
            onValueChange = onTextChange,
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                    text = "Search here ...",
                    color = Color.Black
                )
            },
            textStyle = MovieAppTheme.typography.bold,
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if(text.invoke().isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Black
                    )
                }
            },
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text.invoke())
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )
    }
}