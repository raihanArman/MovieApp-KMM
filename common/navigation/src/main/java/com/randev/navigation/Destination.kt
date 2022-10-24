package com.randev.navigation

/**
 * @author Raihan Arman
 * @date 19/10/22
 */

sealed class Destination(protected val route: String, vararg params: String) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    object HomeScreen : NoArgumentsDestination("home")

    object DetailsScreen : Destination("details", "movie_id") {
        const val MOVIE_ID_KEY = "movie_id"
        operator fun invoke(movieId: Int): String = route.appendParams(
            MOVIE_ID_KEY to movieId
        )
    }

    object MoreUpcomingScreen : NoArgumentsDestination("upcoming")
    object MorePopularScreen : NoArgumentsDestination("popular")
    object SearchScreen : NoArgumentsDestination("search")
    object FavoriteScreen : NoArgumentsDestination("favorite")
}


internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}