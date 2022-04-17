package com.example.tmdbapp.network

import com.squareup.moshi.Json

data class MoviePage(
    val page: Int,
    val results: List<Movie>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)
