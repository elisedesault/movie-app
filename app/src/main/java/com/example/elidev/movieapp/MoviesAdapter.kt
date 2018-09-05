package com.example.elidev.movieapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.elidev.movieapp.MoviesAdapter.MovieViewHolder
import com.example.elidev.movieapp.models.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_item.view.*

class MoviesAdapter(private var movies: List<Movie>,
                    private val onMovieSelectedListener: OnMovieSelectedListener)
    : RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.count()

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onMovieSelectedListener)
    }

    fun updateList(newList: List<Movie>) {
        movies = newList
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        private val TAG: String = MoviesAdapter::class.java.simpleName
        private val FILE_SIZE: String? = "w500"

        fun bind(movie: Movie, onMovieSelectedListener: OnMovieSelectedListener) {

            Picasso.get()
                    .load(movie.posterUrl())
                    .into(itemView.iv_poster)
            itemView.setOnClickListener { onMovieSelectedListener.onItemClick(movie) }

        }

    }

    interface OnMovieSelectedListener {
        fun onItemClick(movie: Movie)
    }
}


