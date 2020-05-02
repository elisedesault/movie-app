package com.example.elidev.movieapp

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.elidev.movieapp.EndlessRecyclerviewScrollListener.OnLoadMoreListener
import com.example.elidev.movieapp.api.PopularMoviesRequest
import com.example.elidev.movieapp.api.PopularMoviesRequestCallbacks
import com.example.elidev.movieapp.models.Movie
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        PopularMoviesRequestCallbacks,
        MoviesAdapter.OnMovieSelectedListener {
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
        EndlessRecyclerviewScrollListener(object : OnLoadMoreListener {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {

            }
        })
    }

    private val MOVIE: String = "movie"

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_settings){
            startActivity(Intent(this, SettingsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

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
}
