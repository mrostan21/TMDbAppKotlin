package com.example.tmdbapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.bindImage
import com.example.tmdbapp.databinding.ListViewItemBinding
import com.example.tmdbapp.network.Movie

class PopularListAdapter : ListAdapter<Movie,PopularListAdapter.MovieViewHolder>(DiffCallback) {

    class MovieViewHolder(private var binding: ListViewItemBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            binding.tvTitle.text = movie.title
            //val imageUri = bindImage(binding.viewModel.)
            //binding.ivPosterImage.setImageURI(@{})
            bindImage(binding.ivPosterImage,movie.posterPath)
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularListAdapter.MovieViewHolder {
        return MovieViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PopularListAdapter.MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
             return ((oldItem.title == newItem.title) && (oldItem.posterPath == newItem.posterPath))
        }
    }
}