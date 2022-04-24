package com.example.tmdbapp.overview

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tmdbapp.R
import com.example.tmdbapp.databinding.FragmentPopularListBinding
import com.example.tmdbapp.network.MovieRepository


class PopularListFragment : Fragment() {

    private val viewModel: PopularListViewModel by viewModels()
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    private val swipeLayout : SwipeRefreshLayout?
         get() = view?.findViewById(R.id.swipeToRefreshPopular)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPopularListBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel
        binding.rvPopular.adapter = PopularListAdapter()
        viewModel.movieList.observe(viewLifecycleOwner) {
            (binding.rvPopular.adapter as PopularListAdapter)?.notifyDataSetChanged()
            isLoading = false
            swipeLayout?.isRefreshing = false
            isLastPage = viewModel.isLastPage

        }

        binding.rvPopular.addOnScrollListener(object :
            PaginationScrollListener(binding.rvPopular.layoutManager as LinearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                viewModel.getNextPopularMovies()
            }
        })


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        swipeLayout?.setOnRefreshListener {
            viewModel.refreshMovies()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.app_bar_search)

        search.isVisible = true
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search by titles!"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getFiltered(newText)
                return true
            }


        })
        super.onCreateOptionsMenu(menu, inflater)
    }




}
