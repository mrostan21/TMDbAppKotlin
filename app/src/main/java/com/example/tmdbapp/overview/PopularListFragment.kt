package com.example.tmdbapp.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tmdbapp.R
import com.example.tmdbapp.databinding.FragmentPopularListBinding



/**
 * This fragment shows the the status of the popular movies web services transaction.
 */
class PopularListFragment : Fragment() {

    private val viewModel: PopularListViewModel by viewModels()

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPopularListBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        binding.rvPopular.adapter = PopularListAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val swipeLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeToRefreshPopular)
        swipeLayout.setOnRefreshListener {
            val action = PopularListFragmentDirections
                .actionPopularListFragmentSelf()
            this.view?.findNavController()?.navigate(action)
            swipeLayout.isRefreshing = false
        }
    }

}
