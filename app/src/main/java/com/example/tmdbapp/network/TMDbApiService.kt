package com.example.tmdbapp.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://api.themoviedb.org"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface TMDbApiService {
    @GET ("/3/movie/popular?api_key=67f050ae8c326d0949a91f7291d4b705&language=en-US&page=1")
   suspend fun getPopularMovies() : String
}

object TMDbApi {
    val retrofitService : TMDbApiService by lazy {
        retrofit.create(TMDbApiService::class.java)
    }
}