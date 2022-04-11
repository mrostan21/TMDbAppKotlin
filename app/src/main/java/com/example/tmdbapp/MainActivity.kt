package com.example.tmdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.data.Movie
import com.example.tmdbapp.network.TMDbApiService

class MainActivity : AppCompatActivity() {

    val VM = OverviewViewModel().status
    val data : List<Movie> = listOf<Movie>(
        Movie(1,"LOTR"),
        Movie(2,"STARWARS"),
        Movie(3,"NARNIA")

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        val test = findViewById<TextView>(R.id.TEST)
        test.text = VM.value
        /*val rvMovies = findViewById<View>(R.id.rv_RecyclerView) as RecyclerView
        val adapter = MovieAdapter(data)
        rvMovies.adapter = adapter
        rvMovies.layoutManager = LinearLayoutManager(this)*/


    }
}