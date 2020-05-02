package com.example.elidev.movieapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

/*id . integer
imdb_id . string or null
original_language . string
original_title . string
overview . string or null
popularity . number
poster_path . string or null
vote_average . number
vote_coverage . integer*/


@Entity(tableName = "movie")
data class Movie(@PrimaryKey(autoGenerate = false) @Json(name="id")val id: Int,
                 @ColumnInfo(name = "imdb_id") @Json(name = "imdb_id") val imdbId: String?,
                 @ColumnInfo(name = "original_language") @Json(name = "original_language") val originalLanguage: String,
                 @ColumnInfo(name = "original_title") @Json(name = "original_title") val originalTitle: String,
                 @ColumnInfo(name = "overview")  @Json(name = "overview") val overview: String,
                 @ColumnInfo(name = "popularity")  @Json(name = "popularity") val popularity: Double,
                 @ColumnInfo(name = "poster_path")  @Json(name = "poster_path") val posterPath: String?,
                 @ColumnInfo(name = "vote_average")   @Json(name = "vote_average") val voteAverage: Double,
                 @ColumnInfo(name = "vote_count")  @Json(name = "vote_count") val voteCount: String
):Serializable