package com.example.tmdbapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.tmdbapp.network.TMDbApi
import kotlinx.coroutines.launch

class OverviewViewModel: ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val listResult = TMDbApi.retrofitService.getPopularMovies()
            _status.value = listResult
        }
    }
}