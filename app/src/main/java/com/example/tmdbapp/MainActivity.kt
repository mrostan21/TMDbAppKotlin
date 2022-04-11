package com.example.tmdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbapp.data.Movie
import com.example.tmdbapp.databinding.ActivityMainBinding
import com.example.tmdbapp.network.TMDbApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private val mvList = mutableListOf<Movie>()
    /*val data : List<Movie> = listOf<Movie>(
        Movie(1,"LOTR"),
        Movie(2,"STARWARS"),
        Movie(3,"NARNIA")

    )*/
    var BASE_URL = "https://api.themoviedb.org/"
    private fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        }

    private fun getMovies(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(TMDbApiService::class.java).getMovies("$query")
            val movieList = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                val movies = movieList?.list?: emptyList()
                    mvList.clear()
                    mvList.addAll(movies)
                    adapter.notifyDataSetChanged()
            }
                else {
                  showError()
                }
            }

        }
    }


    private fun initRecyclerView(){
        adapter = MovieAdapter(mvList)
        binding.rvRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.rvRecyclerView.adapter = adapter
    }

    private fun showError() {
        Toast.makeText(this, "An error has ocurred", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMovies("3/movie/popular?api_key=67f050ae8c326d0949a91f7291d4b705&language=en-US&page=1")
        initRecyclerView()



        /*val rvMovies = findViewById<View>(R.id.rv_RecyclerView) as RecyclerView
        val adapter = MovieAdapter(data)
        rvMovies.adapter = adapter
        rvMovies.layoutManager = LinearLayoutManager(this)*/


    }
}