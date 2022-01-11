package com.example.hw6.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw6.database.dao.MovieDetailsDao
import com.example.hw6.database.dao.MoviePreviewsDao
import com.example.hw6.database.entity.MovieDetailsEntity
import com.example.hw6.database.entity.MoviePreviewEntity

// Room is a database layer on top of an SQLite database
//
// By default, to avoid poor UI performance, Room doesn't allow you to issue queries on the
// main thread. When Room queries return Flow, the queries are automatically run asynchronously on
// a background thread.
//
// Room provides compile-time checks of SQLite statements.

//Your Room database class must be abstract and extend RoomDatabase. Usually, you only need one
// instance of a Room database for the whole app.
@Database(entities = [MovieDetailsEntity::class, MoviePreviewEntity::class], version = 1)
abstract class MoviesDataBase : RoomDatabase() {
    abstract fun getMoviePreviewsDao(): MoviePreviewsDao
    abstract fun getMovieDetailsDao(): MovieDetailsDao
}