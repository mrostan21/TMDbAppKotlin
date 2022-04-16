package com.example.tmdbapp.network

import com.squareup.moshi.Json

data class Movie(
    val id: Int = 0,
    val title: String = " ",
    @Json(name = "release_date") val releaseDate: String = " ",
     val genres: List<Genre> = listOf(),
    @Json(name = "poster_path") val posterPath: String = " ",
    @Json(name = "original_language") val originalLanguage: String = " ",
    val overview: String = " ",
    val popularity: Double = 0.0 ,
    @Json(name = "vote_average") val voteAverage: Double = 0.0

)

