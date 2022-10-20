package com.randev.movieapp_kmm.android.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.randev.domain.model.movie_detail.MovieDetailModel
import com.randev.movieapp_kmm.android.composable.components.progressCircular.ProgressCircularComponent
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

@Composable
fun ContentMovieDetail(
    modifier: Modifier = Modifier,
    data: MovieDetailModel
) {
    Text("Movie ID -> ${data.overview}")
}