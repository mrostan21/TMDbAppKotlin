package com.example.tmdbapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.network.ApiStatus
import com.example.tmdbapp.network.Movie
import com.example.tmdbapp.network.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(movieKey: Int) : ViewModel() {
    //MutableLiveData interna que almacena el estado de la ultima request
    private val _status = MutableLiveData<ApiStatus>()
    private val _movie = MutableLiveData<Movie>()

    //LiveData externa del estado de la request
    val status: LiveData<ApiStatus> = _status
    val movie: LiveData<Movie> = _movie
    val key = movieKey

    /**
     * Llama getPopularMovies() con init para poder mostrar status inmediatamente
     */
    init {
        getMovieDetails()
    }


    /**
     * Obtiene informacion de la pelicula desde la API Retrofit y actualiza
     * [MovieOverView] [List] [LiveData].
     */
    private fun getMovieDetails() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val movieResult = MovieRepository.fetchMovieDetails(key)
                _status.value = ApiStatus.DONE
                _movie.value = movieResult
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _movie.value = Movie()
            }

        }
    }
}