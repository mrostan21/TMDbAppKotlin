package com.example.tmdbapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.themoviedb.org"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("3/movie/popular?api_key=67f050ae8c326d0949a91f7291d4b705&language=en-US&page=1")
    suspend fun getMovies(): MovieOverView
}

object MovieApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}