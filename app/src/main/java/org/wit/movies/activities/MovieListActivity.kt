package org.wit.movies.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activitymovielist.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.movies.R
import org.wit.movies.main.MainApp
import org.wit.movies.models.MovieModel

class MovieListActivity : AppCompatActivity(), MovieListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymovielist)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        // recyclerView.adapter = MovieAdapter(app.movies)
        //recyclerView.adapter = MovieAdapter(app.movies.findAll())
        // recyclerView.adapter = MovieAdapter(app.movies.findAll(), this)
        loadMovies()

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<MovieActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onMovieClick(movie: MovieModel) {
        startActivityForResult(intentFor<MovieActivity>().putExtra("movie_edit", movie), 0)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView is a widget in activity_placemark_list.xml
        //recyclerView.adapter?.notifyDataSetChanged()
        loadMovies()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadMovies() {
        showMovies(app.movies.findAll())
    }

    override fun showMovies(movies: List<MovieModel>) {
        recyclerView.adapter = MovieAdapter(movies, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}