package com.example.assignment3

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment3.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myViewModel : MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(savedInstanceState: Bundle?) called...")
//        setContentView(R.layout.activity_main)
//        trueButton = findViewById(R.id.true_button)
//        falseButton = findViewById(R.id.false_button)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myEditText.setText(myViewModel.findContent())
        binding.myTextView.text = myViewModel.findContent()

        binding.myButton.setOnClickListener { view: View ->
            binding.myEditText.setText(R.string.hello)
            binding.myTextView.setText(R.string.hello)
        }

    }

    override fun onStart() {

        super.onStart()
        Log.d(TAG, "onStart() called...")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() called...")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called...")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called...")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called...")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called...")
    }
}

