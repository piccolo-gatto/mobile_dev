package com.example.memorina

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var catViews: ArrayList<ImageView>
    private var open: ImageView? = null
    private var success = 0
    private val match = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catViews = arrayListOf()
        for (i in 0 until match * 2) {
            val card = findViewById<ImageView>(resources.getIdentifier("card$i", "id", packageName))
            card.tag = i % match
            card.setOnClickListener { openCard(card) }
            catViews.add(card)
        }
        new()
    }

    private fun new() {
        open = null
        success = 0
        randomCards()
        closeCards()
    }

    fun reset(v: View){
        new()
    }

    private fun randomCards() {
        val tags = MutableList(match * 2) { it % match }
        tags.shuffle()
        for (i in 0 until match * 2) {
            catViews[i].tag = tags[i]
        }
    }

    private fun closeCards() {
        for (card in catViews) {
            card.setImageResource(R.drawable.squarecat)
            card.isClickable = true
        }
    }

    private fun cardsImages(tag: Int): Int {
        return when (tag) {
            0 -> R.drawable.cat0
            1 -> R.drawable.cat1
            2 -> R.drawable.cat2
            3 -> R.drawable.cat3
            4 -> R.drawable.cat4
            5 -> R.drawable.cat5
            6 -> R.drawable.cat6
            7 -> R.drawable.cat7
            else -> R.drawable.squarecat
        }
    }

    private fun openCard(card: ImageView) {
        if (card == open || card.drawable == resources.getDrawable(R.drawable.squarecat, null)) {
            return
        }
        card.setImageResource(cardsImages(card.tag.toString().toInt()))
        if (open == null) {
            open = card
        } else {
            if (open!!.tag == card.tag) {
                success++
                open!!.isClickable = false
                card.isClickable = false
                open = null
                if (success == match) {
                    val toast = Toast.makeText(applicationContext,R.string.win, Toast.LENGTH_SHORT)
                    toast.show()
                }
            } else {
                GlobalScope.launch(Dispatchers.Main) {
                    delay(1000)
                    open!!.setImageResource(R.drawable.squarecat)
                    card.setImageResource(R.drawable.squarecat)
                    open = null
                }
            }
        }
    }
}