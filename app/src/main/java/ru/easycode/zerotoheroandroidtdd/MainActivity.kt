package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var textView : TextView
    private lateinit var btn: Button
    private lateinit var linearLayout: LinearLayout
    private var state: State = State.Initial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.titleTextView)
        linearLayout = findViewById(R.id.rootLayout)
        btn = findViewById(R.id.removeButton)

        btn.setOnClickListener {
            state = State.Recreate
            state.apply(linearLayout,btn,textView)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY,state)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
             savedInstanceState.getSerializable(KEY, State::class.java) as State
        }else{
            savedInstanceState.getSerializable(KEY) as State
        }
        state.apply(linearLayout,btn,textView)
    }
    companion object{
        const val KEY = "key"
    }
}

interface State : Serializable{
    fun apply(linearLayout: LinearLayout,button: Button,textView: TextView)

    object Initial : State{
        override fun apply(linearLayout: LinearLayout, button: Button, textView: TextView)  = Unit
    }

    object Recreate : State{
        override fun apply(linearLayout: LinearLayout, button: Button, textView: TextView) {
            linearLayout.removeView(textView)
            button.isEnabled = false

        }
    }
}