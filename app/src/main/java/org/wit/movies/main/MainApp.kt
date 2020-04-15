package org.wit.movies.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.movies.models.MovieMemStore
import org.wit.movies.models.MovieJSONStore
import org.wit.movies.models.MovieStore
import org.wit.movies.models.MovieModel

class MainApp : Application(), AnkoLogger {

   // val movies = ArrayList<MovieModel>()
    lateinit var movies: MovieStore
    //val movies = MovieMemStore()


    override fun onCreate() {
        super.onCreate()
        movies = MovieJSONStore(applicationContext)
        info("Movie started")
    }
}