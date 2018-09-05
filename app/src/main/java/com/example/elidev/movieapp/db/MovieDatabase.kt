package com.example.elidev.movieapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.elidev.movieapp.models.Movie

@Database(entities = arrayOf(Movie ::class), version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE : MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase? {
            if (INSTANCE == null){
                synchronized(MovieDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MovieDatabase::class.java, "movie.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}