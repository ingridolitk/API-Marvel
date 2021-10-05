package com.ingrid.api_marvel

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class SecondMovieDescription : AppCompatActivity() {
    lateinit var actor: TextView
    lateinit var title: TextView
    lateinit var plot: TextView
    lateinit var genre: TextView
    lateinit var imgMovie: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_movie_description)

        actor = findViewById(R.id.txtActor)
        title = findViewById(R.id.titleMovie)
        plot = findViewById(R.id.txtPlot)
        genre = findViewById(R.id.txtGenre)
        imgMovie = findViewById(R.id.imgMovieDesc)

        val value = intent.getStringExtra("actor")
        val titleDesc = intent.getStringExtra("title")
        val plotDesc = intent.getStringExtra("plot")
        val genreDesc = intent.getStringExtra("genre")

        actor.text = value
        title.text = titleDesc
        plot.text = plotDesc
        genre.text = genreDesc

        val intent = intent
        val url = intent.getStringExtra("imgMovie")
        Picasso.get().load(url).into(imgMovie)


    }
}