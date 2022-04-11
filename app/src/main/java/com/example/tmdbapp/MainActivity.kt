package com.example.tmdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.data.Movie

class MainActivity : AppCompatActivity() {

    val data : List<Movie> = listOf<Movie>(
        Movie(1,"LOTR"),
        Movie(2,"STARWARS"),
        Movie(3,"NARNIA")

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvMovies = findViewById<View>(R.id.rv_RecyclerView) as RecyclerView
        val adapter = MovieAdapter(data)
        rvMovies.adapter = adapter
        rvMovies.layoutManager = LinearLayoutManager(this)


    }
}