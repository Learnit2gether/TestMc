package com.example.testmc.data.model

data class MoviesDto(
    val page: Int?,
    val total_results: Long?,
    val total_pages: Int?,
    val results: List<ResultDto>
)
