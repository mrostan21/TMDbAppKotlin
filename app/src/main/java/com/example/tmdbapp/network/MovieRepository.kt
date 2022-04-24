package com.example.tmdbapp.network

import android.util.Log
import kotlinx.coroutines.delay


object MovieRepository {
    private val mutableMovieList: MutableList<Movie> = mutableListOf()
    var currentPage: Int = 1
        private set
    var totalPages: Int = 1
        private set
    var totalResults: Int = 0
        private set
    private var isLoading: Boolean = false

    suspend fun fetchNext() {
        if ((currentPage > totalPages) || isLoading) return
        isLoading = true
        try {
            val listResult = MovieApi.retrofitService.getMovies(currentPage)
            currentPage++
            mutableMovieList.addAll(listResult.results)
            totalPages = listResult.totalPages
            totalResults = listResult.totalResults
            isLoading = false
        } catch (e: Exception) {
            isLoading = false
            throw e
        }

    }

    fun getList(): List<Movie> {
        return mutableMovieList
    }

    suspend fun fetchMovieDetails(movieId: Int): Movie {
        val movieFound = mutableMovieList.firstOrNull { it.id == movieId }
        if (movieFound?.isFullMovie == true) return movieFound

        val movieResult = MovieApi.retrofitService.getMovieDetails(movieId = movieId)
        movieResult.isFullMovie = true
        if (movieFound != null) {
            val movieIndex = mutableMovieList.indexOf(movieFound)
            mutableMovieList[movieIndex] = movieResult
        }
        return movieResult

    }

    suspend fun refreshMovies() {
        mutableMovieList.clear()
        currentPage = 1
        totalPages = 1
        totalResults = 0
        fetchNext()
    }

}
