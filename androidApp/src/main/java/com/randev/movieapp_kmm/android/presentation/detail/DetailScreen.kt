package com.randev.movieapp_kmm.android.presentation.detail

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.randev.domain.model.movie_detail.MovieDetailModel
import com.randev.movieapp_kmm.android.R
import com.randev.movieapp_kmm.android.composable.components.image.BaseImageView
import com.randev.movieapp_kmm.android.composable.components.progressCircular.ProgressCircularComponent
import com.randev.movieapp_kmm.android.composable.components.space.VerticalSpacer
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme
import com.randev.movieapp_kmm.android.presentation.home.components.BASE_URL_BACKDROP_IMAGE
import com.randev.movieapp_kmm.android.presentation.home.components.BASE_URL_IMAGE
import com.randev.movieapp_kmm.android.utils.currentSheetFraction
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject

/**
 * @author Raihan Arman
 * @date 19/10/22
 */

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = getViewModel()
) {
    val detailState by viewModel.observeDetailState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        
        if(detailState.isLoading) {
            ProgressCircularComponent()
        } else {
            detailState.movieDetail?.let { 
                ContentMovieDetail(data = it)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContentMovieDetail(
    modifier: Modifier = Modifier,
    data: MovieDetailModel
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    val currentSheetFraction by remember {
        derivedStateOf {
            scaffoldState.currentSheetFraction
        }
    }

    val radiusAnim by animateDpAsState(
        targetValue = if(currentSheetFraction == 1f) 30.dp else 0.dp
    )

    BottomSheetScaffold(
        modifier = modifier,
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 450.dp,
        sheetContent = {
            BottomSheetContent(
                data = data,
            )
        },
        content = {
            BackgroundContent(
                image = data.backdropPath,
                imageFraction = { currentSheetFraction },
                onCloseClicked = {}
            )
        }
    )
}

@Composable
fun WrappedColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier ,content = content)
}

@Composable
fun BottomSheetContent(data: MovieDetailModel) {

    WrappedColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(25.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = data.originalTitle,
                style = MovieAppTheme.typography.bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            IconButton(
                modifier = Modifier
                    .size(24.dp)
                    .padding(0.dp),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null
                )
            }
        }
        VerticalSpacer(height = 20.dp)
        Text(
            text = "Overview",
            style = MovieAppTheme.typography.bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        VerticalSpacer(height = 10.dp)
        Text(
            text = data.overview,
            style = MovieAppTheme.typography.medium,
            fontSize = 16.sp,
            color = Color.Gray
        )

    }
}

@Composable
fun BackgroundContent(
    image: String,
    imageFraction: () -> Float = {1f},
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {

    val imageUrl = "$BASE_URL_BACKDROP_IMAGE${image}"

    Log.d("Ampas Kuda", "BackgroundContent: $imageUrl")

    WrapperBox(
        modifier = Modifier
            .height(330.dp)
    ) {
        BaseImageView(
            url = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(imageFraction.invoke() + 0.4f)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop
        )
    }

}

@Composable
fun WrapperBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier) {
        content()
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    MovieAppTheme {
        DetailScreen()
    }
}