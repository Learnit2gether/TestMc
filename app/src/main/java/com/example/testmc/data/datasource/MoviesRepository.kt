package com.example.testmc.data.datasource

import com.example.testmc.data.model.MoviesDto
import com.example.testmc.data.network.RetrofitService
import retrofit2.Response

class MoviesRepository() {

    suspend fun getPopularMovies(
        api_key: String,
        page: Int
    ): Response<MoviesDto> = RetrofitService.api.getPopularMovies(api_key, page)

}