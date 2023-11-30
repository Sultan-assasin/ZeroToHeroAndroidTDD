package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var uiState: UiState = UiState.Min("0")
    private val count = Count.Base(step = 2, max = 4, min = 0)
    private lateinit var textView: TextView
    private lateinit var btnIncrement: Button
    private lateinit var btnDecrement: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.countTextView)
        btnIncrement = findViewById(R.id.incrementButton)
        btnDecrement = findViewById(R.id.decrementButton)

        btnIncrement.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            uiState.apply(textView, btnIncrement, btnDecrement)
        }
        btnDecrement.setOnClickListener {
            uiState = count.decrement(textView.text.toString())
            uiState.apply(textView, btnIncrement, btnDecrement)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        else
            savedInstanceState.getSerializable(KEY) as UiState
        uiState.apply(textView, btnIncrement, btnDecrement)

    }

    companion object {
        private const val KEY = "key"
    }
}