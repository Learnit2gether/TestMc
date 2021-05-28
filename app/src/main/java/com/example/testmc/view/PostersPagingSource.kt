package com.example.testmc.view

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testmc.data.API_KEY
import com.example.testmc.data.datasource.MoviesRepository
import com.example.testmc.data.model.ResultDto
import org.koin.core.component.KoinComponent

class PostersPagingSource(val moviesRepository: MoviesRepository): PagingSource<Int,ResultDto>(), KoinComponent {

    override fun getRefreshKey(state: PagingState<Int, ResultDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultDto> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX

            val response = moviesRepository.getPopularMovies(API_KEY,nextPage )
            if (response.isSuccessful) {
                response.body()?.let { movie ->
                    val preKey = movie.page
                    val next = movie.page?.plus( 1 )

                   LoadResult.Page(
                        data = movie.results,
                        prevKey = null,
                        nextKey = next
                    )
                } ?: throw java.lang.Exception("")
            }else {
                LoadResult.Error(Exception("End"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}

