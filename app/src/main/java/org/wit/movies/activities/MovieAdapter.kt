package org.wit.movies.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_movie.view.*
import kotlinx.android.synthetic.main.card_movie.view.*
import kotlinx.android.synthetic.main.card_movie.view.description
import kotlinx.android.synthetic.main.card_movie.view.movieTitle
import kotlinx.android.synthetic.main.card_movie.view.runtime
import org.wit.movies.R
import org.wit.movies.helpers.readImageFromPath
import org.wit.movies.models.MovieModel
import kotlinx.android.synthetic.main.activity_movie.view.trailer as trailer1
import org.wit.movies.activities.MovieAdapter.MainHolder as MainHolder

interface MovieListener {
    fun onMovieClick(movie: MovieModel)
    abstract fun showMovies(findAll: List<MovieModel>)
}

class MovieAdapter constructor(private var movies: List<MovieModel>,
                                   private val listener: MovieListener) : RecyclerView.Adapter<MovieAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val movie = movies[holder.adapterPosition]
        holder.bind(movie, listener)
    }

    override fun getItemCount(): Int = movies.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: MovieModel,  listener : MovieListener) {
            itemView.movieTitle.text = movie.title
            itemView.description.text = movie.description
            itemView.runtime.text = movie.runtime
            itemView.trailer.text = movie.trailer
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, movie.image))
            itemView.setOnClickListener { listener.onMovieClick(movie) }
        }
    }
}