package com.example.tmdbapp.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.tmdbapp.bindTitle
import com.example.tmdbapp.databinding.MovieDetailsFragmentBinding


class MovieDetailsFragment : Fragment() {

    private val movieDetailsFragmentArgs by navArgs<MovieDetailsFragmentArgs>()
    val key = movieDetailsFragmentArgs.movieId
    private val viewModel = MovieDetailsViewModel(key)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MovieDetailsFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel


        return binding.root
    }


}





