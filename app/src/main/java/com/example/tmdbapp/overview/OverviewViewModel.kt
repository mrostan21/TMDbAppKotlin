package com.example.tmdbapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.network.MovieApi
import kotlinx.coroutines.launch

/**
 * El [ViewModel] que esta vinculado al [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
    /**
     * Llama getPopularMovies() con init para poder mostrar status inmediatamente
     */
    init {
        getPopularMovies()
    }

    /**
     * Obtiene informacion de las peliculas populares desde la  API Retrofit y actualiza
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getPopularMovies() {
        viewModelScope.launch {
            try {
                val listResult = MovieApi.retrofitService.getMovies()
                _status.value = "Success: Page ${listResult.page}: ${listResult.results.size} movies retrieved"
            } catch (e : Exception){
                _status.value = "Failure: ${e.message}"
            }

        }
    }
}