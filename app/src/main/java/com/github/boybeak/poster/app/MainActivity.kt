package com.github.boybeak.poster.app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.boybeak.poster.SafePoster

class MainActivity : AppCompatActivity() {

    private val layout by lazy { findViewById<ViewGroup>(R.id.layout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun post(btn: View) {
        val view = View(this).apply {
            setBackgroundColor(Color.DKGRAY)
        }
        layout.addView(view, 100, 100)

        SafePoster.by(btn).liveWith(this).onThrow {

        }.postDelayed(4000L) {
            view.setBackgroundColor(Color.CYAN)
        }
//        layout.removeView(view)
    }

}