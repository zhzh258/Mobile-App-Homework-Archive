package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.simplecalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"start...")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { view: View ->
            Log.d(TAG,"the button is clicked...")
            handleCalculate()
        }
    }

    private fun handleCalculate() {
        val str1: String = binding.firstTextInput.text.toString()
        val str2: String = binding.secondTextInput.text.toString()
        val num1: Float? = str1.toFloatOrNull()
        val num2: Float? = str2.toFloatOrNull()
        Log.d(TAG, "str1 is  $str1")
        Log.d(TAG, "str2 is  $str2")
        val operatorId: Int = binding.operatorRadioGroup.checkedRadioButtonId
        val operatorName: String = findViewById<RadioButton>(operatorId).text.toString()
        Log.d(TAG, "operatorName is  $operatorName")

        if(num1 == null || num2 == null){ //since I'm using android:inputType="numberDecimal", this will not likely to happen
            Toast.makeText(this, getString(R.string.error_prompt_zero), Toast.LENGTH_SHORT).show()
            binding.resultTextView.text = ""
            return
        }
        if(num2 == 0F && operatorName == getString(R.string.over)){
            Toast.makeText(this, getString(R.string.error_prompt_type), Toast.LENGTH_SHORT).show()
            binding.resultTextView.text = ""
            return
        }
        val answer: Float = when(operatorName){
            getString(R.string.plus) -> num1 + num2
            getString(R.string.minus) -> num1 - num2
            getString(R.string.times) -> num1 * num2
            getString(R.string.over) -> num1 / num2
            else -> 0F
        }
        Log.d(TAG, answer.toString())
        binding.resultTextView.text = answer.toString()
    }
}