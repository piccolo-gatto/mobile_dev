package com.example.guessnumber


import android.content.Intent
import android.icu.lang.UCharacter.NumericType
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onGuessClick(view: View) {

        val begin = findViewById<View>(R.id.begin) as EditText
        val end = findViewById<View>(R.id.end) as EditText
        val beginNum = begin.text.toString().toInt()
        val endNum = end.text.toString().toInt()

        if (beginNum < endNum && (endNum - beginNum) > 1) {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("begin", beginNum)
            intent.putExtra("end", endNum)
            startActivity(intent)
        }
        else {
            Toast.makeText(this, "Первое число должно быть меньше второго!", Toast.LENGTH_SHORT).show()
        }
    }
}