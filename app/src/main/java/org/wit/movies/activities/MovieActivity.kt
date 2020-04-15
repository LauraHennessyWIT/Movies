package org.wit.movies.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_movie.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.movies.R
import org.wit.movies.helpers.readImage
import org.wit.movies.helpers.readImageFromPath
import org.wit.movies.helpers.showImagePicker
import org.wit.movies.main.MainApp
import org.wit.movies.models.MovieModel

class MovieActivity : AppCompatActivity(), AnkoLogger {

    var edit = false
    val IMAGE_REQUEST = 1
    var movie = MovieModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)


        if (intent.hasExtra("movie_edit")) {
            edit = true
            movie = intent.extras?.getParcelable<MovieModel>("movie_edit")!!
            movieTitle.setText(movie.title)
            description.setText(movie.description)
            runtime.setText(movie.runtime)
            trailer.setText(movie.trailer)
            movieImage.setImageBitmap(readImageFromPath(this, movie.image))
            if (movie.image != null) {
                chooseImage.setText(R.string.change_movie_image)
            }
            btnAdd.setText(R.string.save_movie)
        }


        btnAdd.setOnClickListener() {
            movie.title = movieTitle.text.toString()
            movie.description = description.text.toString()
            movie.runtime = runtime.text.toString()
            movie.trailer = trailer.text.toString()
            if (movie.title.isEmpty()) {
                toast(R.string.enter_movie_title)
            }
            else if (movie.description.isEmpty()) {
                toast(R.string.enter_movie_description)
            }
            else if (movie.runtime.isEmpty()) {
                toast(R.string.enter_movie_runtime)
            }
            else if (movie.trailer.isEmpty()) {
                toast(R.string.enter_movie_trailer)
            }
            else {
                if (edit) {
                    app.movies.update(movie.copy())
                } else {
                    app.movies.create(movie.copy())
                }
            }
            info("add Button Pressed: $movieTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }


        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie, menu)
        if (edit && menu != null) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.movies.delete(movie)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    movie.image = data.getData().toString()
                    movieImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_movie_image)
                }
            }
        }
    }
}


