package com.example.elidev.movieapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.elidev.movieapp.models.Movie

@Dao
interface MovieDao {

    @Query("SELECT * from movie")
    fun getAll(): List<Movie>

    @Insert(onConflict = REPLACE)
    fun insert(movie: Movie)

    @Query("DELETE from movie")
    fun deleteAll()
}