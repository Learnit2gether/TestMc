package com.example.testmc.data.network

import com.example.testmc.data.model.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<MoviesDto>

}