package com.example.tmdbapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.themoviedb.org"
private const val API_KEY = "67f050ae8c326d0949a91f7291d4b705" //Posible fallo de seguridad, cambiar api key de lugar
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
enum class ApiStatus {LOADING, ERROR, DONE}

interface ApiService {
    @GET("3/movie/popular?api_key=${API_KEY}&language=en-US&page=1")
    suspend fun getMovies(): MoviePage

    @GET("3/movie/{id}?api_key=${API_KEY}&language=en-US&page=1")
    suspend fun getMovieDetails(@Path("id") movieId:Int): Movie
}

object MovieApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}