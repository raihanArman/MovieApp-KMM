package com.randev.movieapp_kmm.android.presentation.main

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.randev.core.wrapper.Resource
import com.randev.domain.model.DataMovieModel
import com.randev.domain.model.MovieModel
import com.randev.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last

/**
 * @author Raihan Arman
 * @date 19/10/22
 */

class MainDataSource(
    private val useCase: GetMovieUseCase
): PagingSource<Int, DataMovieModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataMovieModel> {
        val currentPage = params.key ?: 1
        return when(val movieResponse = useCase(currentPage).last()) {
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
        return state.anchorPosition
    }
}