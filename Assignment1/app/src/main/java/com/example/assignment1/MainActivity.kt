package com.example.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var myButton: Button;
    private lateinit var myText: TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myButton = findViewById(R.id.hello_world_button)
        myText = findViewById(R.id.hello_world_text)

        myButton.setOnClickListener {view: View ->
            myText.text = getString(R.string.hello_world_text_content)
        }
    }
}