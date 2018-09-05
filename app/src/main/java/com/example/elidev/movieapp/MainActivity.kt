package com.example.elidev.movieapp

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.example.elidev.movieapp.api.PopularMoviesRequest
import com.example.elidev.movieapp.api.PopularMoviesRequestCallbacks
import com.example.elidev.movieapp.models.Movie
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PopularMoviesRequestCallbacks, MoviesAdapter.OnMovieSelectedListener {
    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var moviesAdapter: MoviesAdapter

    private lateinit var linearLayoutManager: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()

        initRecyclerView()
        displayListTitle()
        loadMovies()
    }

    private fun displayListTitle() {
        supportActionBar?.title = resources.getString(R.string.popular_movies_title)
    }

    private fun loadMovies() {
        compositeDisposable.add(PopularMoviesRequest().getPopularMovies(this))
    }

    private fun initRecyclerView() {
        rvMovies.setHasFixedSize(true)

        if (this.applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            linearLayoutManager = GridLayoutManager(this, 2)
        } else {
            linearLayoutManager = GridLayoutManager(this, 4)
        }

        rvMovies.layoutManager = linearLayoutManager
        moviesAdapter = MoviesAdapter(emptyList(), this)
        rvMovies.adapter = moviesAdapter
    }

    private val MOVIE: String = "movie"

    override fun onItemClick(movie: Movie) {
        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra(MOVIE, movie)
        startActivity(intent)
    }


    override fun onSuccess(list: List<Movie>) {
        moviesAdapter.updateList(list)
    }

    override fun onError() {
        Log.d(MainActivity::class.java.name, "An issue occurred while charging the list")
    }

    //DONE 1- Create Movie data class with Room annotations
    //DONE 2- Create MovieDao class to retrieve/register movie's details
    //DONE 3- Create MovieDatabase class
    //DONE 4- Make an item UI xml
    //DONE 5- Make a list UI xml
    //DONE 6- Display list from MainActivity thanks to a method that update the adapter in the callback method onSuccess()
    //DONE 8- Retrieve each movie poster thanks to Picasso
    //DONE 9- Bind the list item views with the data
}
