package com.example.elidev.movieapp.api

import android.util.Log
import com.example.elidev.movieapp.api.RetrofitImpl.Companion.retrofitService
import com.example.elidev.movieapp.models.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PopularMoviesRequest {

    fun getPopularMovies(callbacks: PopularMoviesRequestCallbacks): Disposable {
        val requestInterface = retrofitService().create(PopularMoviesRequestInterface::class.java)
        return requestInterface.getPopularMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { it.result }
                .subscribe(
                        { list -> callbacks.onSuccess(list) },
                        { e ->
                            Log.e("PopularMoviesRequest", "an error occurred", e)
                            callbacks.onError()
                         }
                )
    }

   /* private fun toMovieForUi(movies: List<Movie>): List<MovieForUi> {
        movies
                .map { movie -> MovieForUI(movie.id, postPath.helper(movie.posterPath)) }
    }*/


}

interface PopularMoviesRequestCallbacks {
    fun onSuccess(list: List<Movie>)
    fun onError()
}
