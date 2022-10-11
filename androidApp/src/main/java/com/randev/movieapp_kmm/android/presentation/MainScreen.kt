package com.randev.movieapp_kmm.android.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

/**
 * @author Raihan Arman
 * @date 11/10/22
 */

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val mainState by viewModel.observeMovie.collectAsState()
   Box(
       modifier = Modifier
           .fillMaxSize(),
       contentAlignment = Alignment.Center
   ) {
       Text(
           text = mainState.toString(),
           fontSize = 20.sp
       )
   }
}