package com.example.randomfilm



import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.gson.Gson
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var movies : MutableList<Movie>
    private lateinit var movieTitle: TextView
    private lateinit var movieYear: TextView
    private lateinit var movieCountry: TextView
    private lateinit var movieMinutes: TextView
    private lateinit var movieGenre: TextView
    private lateinit var movieRating: TextView
    val r = Random()


    override fun onStart() {
        super.onStart()
        Log.d("mytag", "onStart()" )
    }

    override fun onStop() {
        super.onStop()
        Log.d("mytag", "onStop()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieTitle = findViewById(R.id.title)
        movieMinutes = findViewById(R.id.minutes)
        movieRating = findViewById(R.id.rating)
        movieGenre = findViewById(R.id.genre)
        movieYear = findViewById(R.id.year)
        movieCountry = findViewById(R.id.country)
        movieTitle.text = getString(R.string.app_name)
        //movies = resources.getStringArray(R.array.movies).toMutableList()
        val gson = Gson()
        val stream = resources.openRawResource(R.raw.movies)
        val reader = InputStreamReader(stream)
        movies = gson.fromJson(reader, Movies::class.java).movies.toMutableList()

        Log.d("mytag", "Loaded movies ${movies.size}")
    }

    fun onNextClick(view: View) {
        if (movies.isEmpty()) {
            movieTitle.text = getString(R.string.none)
            movieRating.text = ""
            movieGenre.text = ""
            movieCountry.text = ""
            movieMinutes.text = ""
            movieYear.text = ""
            Log.d("mytag", getString(R.string.none))
        } else {
            val i = r.nextInt(movies.size)
            val movie = movies[i]
            movies.removeAt(i)
            movieTitle.text = movie.name
            movieRating.text = movie.rating.toString()
            movieGenre.text = movie.genre.toString()
            movieCountry.text = movie.country.toString()
            movieMinutes.text = movie.minutes.toString() + " minutes"
            movieYear.text = movie.year.toString()
            Log.d("mytag", movie.name)
        }

    }

    fun onClearClick(view: View) {
        val gson = Gson()
        val stream = resources.openRawResource(R.raw.movies)
        val reader = InputStreamReader(stream)
        movies = gson.fromJson(reader, Movies::class.java).movies.toMutableList()
        movieTitle.text = getString(R.string.app_name)
        Log.d("mytag", "restart")
    }
}
