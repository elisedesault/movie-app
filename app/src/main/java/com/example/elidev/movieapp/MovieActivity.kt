package com.example.elidev.movieapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.elidev.movieapp.models.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity: AppCompatActivity() {

    private val MOVIE: String ="movie"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        if(intent != null && intent.hasExtra(MOVIE)){
        val movieSelected = intent.getSerializableExtra(MOVIE) as Movie

            Picasso.get()
                    .load(movieSelected.posterUrl())
                    .into(ivPoster)

            supportActionBar?.title = movieSelected.originalTitle
            tvOverview.text = movieSelected.overview
            tvPopularity.text = movieSelected.popularity.toString()
            tvVoteAverage.text = movieSelected.voteAverage.toString()
            tvVoteCount.text = movieSelected.voteCount

        }
    }

}
