package org.wit.movies.models

interface MovieStore {
    fun findAll(): List<MovieModel>
    fun create(movie: MovieModel)
    fun update(movie: MovieModel)
    fun delete(movie: MovieModel)

}