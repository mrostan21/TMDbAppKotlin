package com.example.tmdbapp.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.network.ApiStatus
import com.example.tmdbapp.network.Movie
import com.example.tmdbapp.network.MovieApi
import com.example.tmdbapp.network.MovieRepository
import kotlinx.coroutines.launch


/**
 * El ViewModel que esta vinculado al PopularListFragment.
 */
class PopularListViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()
    private val _movieList = MutableLiveData<List<Movie>>()

    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus> = _status
    val movieList: LiveData<List<Movie>> = _movieList

    /**
     * Llama getPopularMovies() con init para poder mostrar status inmediatamente
     */
    init {
        getPopularMovies()
    }

    /**
     * Obtiene informacion de las peliculas populares desde la  API Retrofit y actualiza
     * MovieOverView, List, LiveData.
     */
    private fun getPopularMovies() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                MovieRepository.fetchNext()
                val listResult = MovieRepository.getList()
                _status.value = ApiStatus.DONE
                _movieList.value = listResult
            } catch (e: Exception) {

                _status.value = ApiStatus.ERROR
                _movieList.value = listOf()
            }

        }
    }

     fun getNextPopularMovies() {
        viewModelScope.launch {
            try {
                MovieRepository.fetchNext()
                val listResult = MovieRepository.getList()
                _movieList.value = listResult
            } catch (e: Exception) {

            }

        }
    }


}