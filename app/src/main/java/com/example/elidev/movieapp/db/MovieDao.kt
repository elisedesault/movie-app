package com.example.elidev.movieapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
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