package com.example.tmdbapp.network

import com.squareup.moshi.Json

data class MoviePage(
    val page: Int,
    val results: List<Movie>
)
