package com.example.tmdbapp.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tmdbapp.R
import com.example.tmdbapp.databinding.MovieDetailsFragmentBinding


class MovieDetailsFragment : Fragment() {


    private lateinit var key: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            key = it.getInt("movieId").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = MovieDetailsViewModel(key.toInt())
        val binding = MovieDetailsFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val swipeLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeToRefreshDetails)
        swipeLayout.setOnRefreshListener {
            /*val action = MovieDetailsFragmentDirections
                .actionMovieDetailsFragmentSelf(key.toInt())
            this.view?.findNavController()?.navigate(action)*/
            val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(PopularListFragmentDirections.actionPopularListFragmentToMovieDetailsFragment(key.toInt()))
            }

            swipeLayout.isRefreshing = false
        }
    }


}





