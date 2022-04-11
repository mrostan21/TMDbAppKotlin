package com.example.tmdbapp.network

import com.example.tmdbapp.data.Movie
import com.example.tmdbapp.data.MovieList
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


interface TMDbApiService {
    @GET
    suspend fun getMovies(@Url url: String) : Response<MovieList>





}



