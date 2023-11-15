package ru.easycode.zerotoheroandroidtdd

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var textView : TextView
    private lateinit var buttonClick : Button
    private var visible : Boolean = false
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.titleTextView)
        buttonClick = findViewById(R.id.hideButton)

        buttonClick.setOnClickListener {
            if(visible){
                textView.visibility = View.VISIBLE
                visible = false
            }
            else {
                visible = true
                textView.visibility = View.INVISIBLE
            }


        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("key", visible)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        visible = savedInstanceState.getBoolean("key")
        if(visible) textView.visibility = View.INVISIBLE else View.VISIBLE
    }


}