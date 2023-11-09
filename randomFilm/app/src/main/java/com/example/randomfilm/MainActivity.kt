package com.example.randomfilm


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var movies : MutableList<String>
    private lateinit var movieTitle: TextView
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
        movies = resources.getStringArray(R.array.movies).toMutableList()
        Log.d("mytag", movies[0])
    }

    fun onNextClick(view: View) {
        if (movies.isEmpty()) {
            movieTitle.text = getString(R.string.none)
            Log.d("mytag", getString(R.string.none))
        } else {
            val i = r.nextInt(movies.size)
            val movie = movies[i]
            movies.removeAt(i)
            movieTitle.text = movie
            Log.d("mytag", movie)
        }

    }

    fun onClearClick(view: View) {
        movies = resources.getStringArray(R.array.movies).toMutableList()
        movieTitle.text = getString(R.string.app_name)
        Log.d("mytag", "restart")
    }
}