package com.randev.movieapp_kmm.android.presentation.detail

import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.randev.domain.model.movie_detail.MovieDetailModel
import com.randev.movieapp_kmm.android.composable.components.image.BaseImageView
import com.randev.movieapp_kmm.android.composable.components.progressCircular.ProgressCircularComponent
import com.randev.movieapp_kmm.android.composable.components.space.VerticalSpacer
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme
import com.randev.movieapp_kmm.android.presentation.detail.components.CastItem
import com.randev.movieapp_kmm.android.presentation.detail.components.GenreItem
import com.randev.movieapp_kmm.android.composable.components.card.BASE_URL_BACKDROP_IMAGE
import com.randev.movieapp_kmm.android.composable.components.card.PhotoItem
import com.randev.movieapp_kmm.android.composable.components.favorite.FavoriteIcon
import com.randev.movieapp_kmm.android.composable.components.space.HorizontalSpacer
import com.randev.movieapp_kmm.android.utils.currentSheetFraction
import org.koin.androidx.compose.getViewModel

/**
 * @author Raihan Arman
 * @date 19/10/22
 */

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = getViewModel()
) {
    val detailState by viewModel.observeDetailState.collectAsState()
    val isFavorite = viewModel.isFavoriteMovie

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        ContentMovieDetail(
            state = detailState,
            onCloseClicked = { viewModel.onBackButtonClicked() },
            onClickFavorite = {
                viewModel.insertDeleteFavorite(it)
            },
            isFavorite = isFavorite
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContentMovieDetail(
    modifier: Modifier = Modifier,
    state: DetailState,
    onCloseClicked: () -> Unit,
    onClickFavorite: (MovieDetailModel) -> Unit,
    isFavorite: Boolean
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
                state = state,
                onClickFavorite = onClickFavorite,
                isFavorite = isFavorite
            )
        },
        content = {
            BackgroundContent(
                state = state,
                imageFraction = { currentSheetFraction },
                onCloseClicked = onCloseClicked
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
fun BottomSheetContent(state: DetailState, onClickFavorite: (MovieDetailModel) -> Unit, isFavorite: Boolean) {
    println("State -> $state")
    WrappedColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(25.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        state.movieDetail?.let { data ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = data.originalTitle,
                    style = MovieAppTheme.typography.bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                HorizontalSpacer(width = 30.dp)
                FavoriteIcon(
                    modifier = Modifier
                        .size(30.dp),
                    onClickFavorite = {
                        println("Favorite -> click")
                        onClickFavorite(it)
                    },
                    data = data,
                    isFavorite = isFavorite
                )
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
            VerticalSpacer(height = 20.dp)
            Text(
                text = "Genre",
                style = MovieAppTheme.typography.bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            VerticalSpacer(height = 10.dp)
            FlowRow(
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 10.dp
            ) {
                data.genres.forEach {
                    it?.let { genre ->
                        GenreItem(genre = genre)
                    }
                }
            }
        }

        state.castList?.let {  casts ->
            VerticalSpacer(height = 30.dp)
            Text(
                text = "Cast",
                style = MovieAppTheme.typography.bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            VerticalSpacer(height = 10.dp)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(casts) {
                    CastItem(cast = it)
                }
            }
        }

        state.movieDetail?.images?.let { images ->
            if(images.isNotEmpty()) {
                VerticalSpacer(height = 20.dp)
                Text(
                    text = "Photos",
                    style = MovieAppTheme.typography.bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                VerticalSpacer(height = 10.dp)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(images) { image ->
                        image?.let {
                            PhotoItem(url = it.url)
                        }
                    }
                }
            }
        }

        if (state.isLoading) {
            ProgressCircularComponent()
        }

    }
}

@Composable
fun BackgroundContent(
    state: DetailState,
    imageFraction: () -> Float = {1f},
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    WrapperBox(
        modifier = Modifier
            .height(330.dp)
    ) {
        state.movieDetail?.let {

            val imageUrl = "$BASE_URL_BACKDROP_IMAGE${it.backdropPath}"
            BaseImageView(
                url = imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(imageFraction.invoke() + 0.4f)
                    .align(Alignment.TopStart),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.Black.copy(alpha = 0.2f)
                ) {
                    IconButton(
                        onClick = onCloseClicked,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(5.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }

        }
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