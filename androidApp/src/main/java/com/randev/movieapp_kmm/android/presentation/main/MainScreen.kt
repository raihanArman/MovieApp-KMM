package com.randev.movieapp_kmm.android.presentation.main

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.randev.movieapp_kmm.android.composable.style.MovieAppTheme
import com.randev.movieapp_kmm.android.presentation.detail.DetailScreen
import com.randev.movieapp_kmm.android.presentation.home.HomeScreen
import com.randev.movieapp_kmm.android.presentation.more_popular.MorePopularScreen
import com.randev.movieapp_kmm.android.presentation.more_upcoming.MoreUpcomingScreen
import com.randev.movieapp_kmm.android.presentation.search.SearchScreen
import com.randev.navigation.Destination
import com.randev.navigation.NavHostApp
import com.randev.navigation.NavigationIntent
import com.randev.navigation.composable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.androidx.compose.getViewModel

/**
 * @author Raihan Arman
 * @date 19/10/22
 */

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = getViewModel()
) {
    val navController = rememberNavController()

    NavigationEffects(
        navigationChannel = mainViewModel.navigationChannel,
        navHostController = navController
    )

    MovieAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHostApp(
                navController = navController,
                startDestination = Destination.HomeScreen
            ) {
                composable(destination = Destination.HomeScreen) {
                    HomeScreen()
                }
                composable(destination = Destination.DetailsScreen) {
                    DetailScreen()
                }
                composable(destination = Destination.MoreUpcomingScreen) {
                    MoreUpcomingScreen()
                }
                composable(destination = Destination.MorePopularScreen) {
                    MorePopularScreen()
                }
                composable(destination = Destination.SearchScreen) {
                    SearchScreen()
                }
            }
        }
    }

}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true){
                return@collect
            }
            when(intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route!!, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute){
                                inclusive = intent.inclusive
                            }
                        }
                    }
                }
            }
        }
    }
}