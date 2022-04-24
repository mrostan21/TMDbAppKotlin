package com.example.tmdbapp.overview

import android.util.Log
import androidx.lifecycle.*
import com.example.tmdbapp.network.ApiStatus
import com.example.tmdbapp.network.Movie
import com.example.tmdbapp.network.MovieApi
import com.example.tmdbapp.network.MovieRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher


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
    var isLastPage: Boolean = false
    var isSearching: Boolean = false

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, exception ->
        Log.e("Exception", "Refreshing failed", exception)
    }

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

    fun getNextPopularMovies(): Boolean {
        if (isSearching) {
            return false
        }
        viewModelScope.launch {
            try {
                MovieRepository.fetchNext()
                val listResult = MovieRepository.getList()
                _movieList.value = listResult
                isLastPage = (MovieRepository.currentPage == MovieRepository.totalPages)
            } catch (e: Exception) {

            }

        }
        return true
    }

    fun getFiltered(search: String?) {
        isSearching = !search.isNullOrEmpty()
        val listResult =
            MovieRepository.getList().filter { it.title.lowercase().contains(search!!.lowercase()) }
        _movieList.value = listResult


    }

    fun refreshMovies() {
        viewModelScope.launch(coroutineExceptionHandler) {
            MovieRepository.refreshMovies()
            _movieList.value = MovieRepository.getList()
        }
    }

}