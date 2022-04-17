package com.example.tmdbapp.network

import android.util.Log


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
        if ((currentPage > totalPages) || isLoading ) return
        isLoading = true
        try {
            val listResult = MovieApi.retrofitService.getMovies(currentPage)
            currentPage++
            mutableMovieList.addAll(listResult.results)
            totalPages = listResult.totalPages
            totalResults = listResult.totalResults
            isLoading = false
        } catch (e: Exception) {
            Log.e("TMDb", "exception", e);
            isLoading = false
            throw e
        }

    }

    fun getList(): List<Movie> {
        return mutableMovieList
    }
}
