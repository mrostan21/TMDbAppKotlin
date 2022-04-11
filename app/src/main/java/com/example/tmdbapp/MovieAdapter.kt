package com.example.tmdbapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.data.Movie
import com.example.tmdbapp.data.MovieList
import com.example.tmdbapp.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class MovieAdapter (private val dataSet: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ListItemBinding.bind(view)
        fun bind (item : Movie){
            binding.tvTitle.text = item.title
            Picasso.get().load(item.poster_path).into(binding.ivPoster)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = dataSet[position]
        viewHolder.bind(item)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}