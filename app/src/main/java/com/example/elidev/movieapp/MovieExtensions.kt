package com.example.elidev.movieapp

import android.net.Uri
import com.example.elidev.movieapp.models.Movie

private const val FILE_SIZE = "w500"

fun Movie.posterUrl(): String =
        Uri.parse(BuildConfig.TMDB_IMAGE_BASE_URL).buildUpon()
                .appendPath(FILE_SIZE)
                .appendEncodedPath(posterPath)
                .toString()

