package com.example.colortilesviewsk

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import android.util.Log
import android.widget.Toast
import kotlin.random.Random


data class Coord(val x: Int, val y: Int)
class MainActivity : AppCompatActivity() {

    lateinit var tiles: Array<Array<View>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tiles = arrayOf(
            arrayOf(findViewById(R.id.t00), findViewById(R.id.t01), findViewById(R.id.t02), findViewById(R.id.t03)),
            arrayOf(findViewById(R.id.t10), findViewById(R.id.t11), findViewById(R.id.t12), findViewById(R.id.t13)),
            arrayOf(findViewById(R.id.t20), findViewById(R.id.t21), findViewById(R.id.t22), findViewById(R.id.t23)),
            arrayOf(findViewById(R.id.t30), findViewById(R.id.t31), findViewById(R.id.t32), findViewById(R.id.t33))
            )

        initField()
    }

    fun initField() {
        for (row in tiles) {
            for (tile in row) {
                if (Random.nextBoolean()) {
                    tile.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.bright, null))
                } else {
                    tile.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.dark, null))
                }
            }
        }
    }

    fun getCoordFromString(s: String): Coord {
        val (x, y) = s.chunked(1)
        Log.i("coordinates", "x: $x, y: $y")
        return Coord(x.toInt(), y.toInt())
    }

    fun changeColor(view: View) {
        val brightColor = resources.getColor(R.color.bright)
        val darkColor = resources.getColor(R.color.dark)
        val drawable = view.background as ColorDrawable
        if (drawable.color == brightColor ) {
            view.setBackgroundColor(darkColor)
        }
        else {
            view.setBackgroundColor(brightColor)
        }
    }

    fun onClick(v: View) {
        val coord = getCoordFromString(v.getTag().toString())
        changeColor(v)

        for (i in 0..3) {
            changeColor(tiles[coord.x][i])
            changeColor(tiles[i][coord.y])
        }

        if (checkVictory()) {
            Toast.makeText(this, "Победа", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkVictory() : Boolean {
        val firstColor = (tiles[0][0].background as ColorDrawable).color
        for (row in tiles) {
            for (view in row) {
                val currentColor = (view.background as ColorDrawable).color
                if (currentColor != firstColor) {
                    return false
                }
            }
        }
        return true
    }
}