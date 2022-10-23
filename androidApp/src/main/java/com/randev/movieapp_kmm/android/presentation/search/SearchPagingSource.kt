package com.randev.movieapp_kmm.android.presentation.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.randev.core.wrapper.Resource
import com.randev.domain.model.movie_list.DataMovieModel
import com.randev.domain.usecase.GetMovieUpcomingUseCase
import com.randev.domain.usecase.GetMovieUseCase
import com.randev.domain.usecase.SearchMovieUseCase
import com.randev.domain.usecase.SearchRequest
import kotlinx.coroutines.flow.last

/**
 * @author Raihan Arman
 * @date 19/10/22
 */

class SearchPagingSource(
    private val useCase: SearchMovieUseCase,
    private val query: String
): PagingSource<Int, DataMovieModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataMovieModel> {
        val currentPage = params.key ?: 1
        return when(val movieResponse = useCase(SearchRequest(query, currentPage)).last()) {
            is Resource.Success -> {
                val movie = movieResponse.model?.results ?: emptyList()
                val endOfPaginationReached = movie.isEmpty() || movieResponse.model?.page == 0
                if (movie.isNotEmpty()) {
                    LoadResult.Page(
                        data = movie,
                        prevKey = if (currentPage == 1) null else currentPage - 1,
                        nextKey = if (endOfPaginationReached) null else currentPage + 1
                    )
                } else {
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                    )
                }
            }
            else -> {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }
    }
    override fun getRefreshKey(state: PagingState<Int, DataMovieModel>): Int? {
        return null
    }
}