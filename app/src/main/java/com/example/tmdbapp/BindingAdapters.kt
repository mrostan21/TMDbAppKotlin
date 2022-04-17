package com.example.tmdbapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tmdbapp.network.ApiStatus
import com.example.tmdbapp.network.Movie
import com.example.tmdbapp.overview.PopularListAdapter

private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w400"
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val imgUri = "$BASE_IMAGE_URL$imgUrl".toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Movie>?) {
    val adapter = recyclerView.adapter as PopularListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {

    when (status) {

        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
            }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
@BindingAdapter("apiStatusText")
fun bindStatusText(statusTextView: TextView, status: ApiStatus?) {

    when (status) {

        ApiStatus.LOADING -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.setText(R.string.loading)
        }
        ApiStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.setText(R.string.dwnld_error)
        }
        ApiStatus.DONE -> {
            statusTextView.visibility = View.GONE
        }

    }
}


@BindingAdapter("setTitle")
fun bindTitle(
    textView: TextView,
    data: LiveData<Movie>?
) {
    if (data?.value?.title != null)
        textView.text = "Title: ${data.value?.title}"
}
@BindingAdapter("setRelease")
fun bindRelease(
    textView: TextView,
    data: LiveData<Movie>?
) {
    if (data?.value?.releaseDate != null )
        textView.text = "Release date: ${data?.value?.releaseDate}"
}

@BindingAdapter("setOverview")
fun bindOverview(
    textView: TextView,
    data: LiveData<Movie>?
) {
    if (data?.value?.overview != "")
        textView.text = data?.value?.overview
}

@BindingAdapter("setOriginalLang")
fun bindLang(
    textView: TextView,
    data: LiveData<Movie>?
) {
    if (data?.value?.releaseDate != null)
        textView.text = "Original language: ${data?.value?.originalLanguage?.uppercase()}"
}

@BindingAdapter("setVoteAvg")
fun bindVoteAvg(
    textView: TextView,
    data: LiveData<Movie>?
) {
    if (data?.value?.voteAverage != null && data?.value?.voteAverage != 0.0)
        textView.text = "Vote average: ${data?.value?.voteAverage.toString()}"
}

@BindingAdapter("setPopularity")
fun bindPopularity(
    textView: TextView,
    data: LiveData<Movie>?
) {
    if (data?.value?.popularity != null && data?.value?.popularity != 0.0 )
        textView.text = "Popularity: ${data?.value?.popularity.toString()}"
}

@BindingAdapter("setGenre")
fun bindGenre(
    textView: TextView,
    data: LiveData<Movie>?
) {

    val commaSeparatedGenres = data?.value?.genres?.joinToString {
        it.name
    }
     if (commaSeparatedGenres != null) textView.text = "Genre(s): $commaSeparatedGenres"

}
