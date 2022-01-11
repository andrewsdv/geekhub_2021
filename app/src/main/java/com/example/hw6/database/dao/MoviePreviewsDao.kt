package com.example.hw6.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hw6.database.entity.MoviePreviewEntity
import kotlinx.coroutines.flow.Flow


/*
* In the DAO (data access object), you specify SQL queries and associate them with method calls.
* The compiler checks the SQL and generates queries from convenience annotations for common queries,
* such as @Insert. Room uses the DAO to create a clean API for your code.
*
* The DAO must be an interface or abstract class.
*
* By default, all queries must be executed on a separate thread.
*
* Room has Kotlin coroutines support. This allows your queries to be annotated with the suspend modifier and then called from a coroutine or from another suspension function.
* */
@Dao
abstract class MoviePreviewsDao {
    // When data changes, you usually want to take some action, such as displaying the updated data
    // in the UI. This means you have to observe the data so when it changes, you can react.
    //
    //To observe data changes you will use Flow from kotlinx-coroutines. Use a return value of type
    // Flow in your method description, and Room generates all necessary code to update the Flow
    // when the database is updated.

    // A Flow is an async sequence of values
    //
    //Flow produces values one at a time (instead of all at once) that can generate values from
    // async operations like network requests, database calls, or other async code. It supports
    // coroutines throughout its API, so you can transform a flow using coroutines as well.
    @Query("SELECT * FROM movie")
    abstract fun fetchMovies(): Flow<List<MoviePreviewEntity>>


    // The @Insert annotation is a special DAO method annotation where you don't have to provide
    // any SQL! (There are also @Delete and @Update annotations for deleting and updating rows.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addMovies(movies: List<MoviePreviewEntity>)
}