package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState: Serializable {

    fun apply(textView: TextView,increment: Button, decrement: Button)
    data class Base(private val text: String) : UiState {
        override fun apply(textView: TextView, increment: Button, decrement: Button) {
            textView.text = text
            decrement.isEnabled = true
            increment.isEnabled = true
        }

    }

    data class Max(private val text: String) : UiState {
        override fun apply(textView: TextView, increment: Button, decrement: Button) {
            textView.text = text
            increment.isEnabled = false
            decrement.isEnabled = true
        }

    }

    data class Min(private val text: String) : UiState {
        override fun apply(textView: TextView, increment: Button, decrement: Button) {
            textView.text = text
            increment.isEnabled = true
            decrement.isEnabled = false
        }

    }
}