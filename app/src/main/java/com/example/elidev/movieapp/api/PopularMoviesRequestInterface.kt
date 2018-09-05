package com.example.elidev.movieapp.api

import com.example.elidev.movieapp.BuildConfig
import com.example.elidev.movieapp.models.Movie
import com.example.elidev.movieapp.models.ResponseBodyWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface PopularMoviesRequestInterface {

    @GET("movie/popular")
    fun getPopularMovies(
            @Query("api_key") apiKey : String = BuildConfig.TMDB_API_KEY,
            @Query("language") language : String = BuildConfig.LANGUAGE,
            @Query("page") page : String = BuildConfig.PAGE
    ): Observable<ResponseBodyWrapper<Movie>>

}