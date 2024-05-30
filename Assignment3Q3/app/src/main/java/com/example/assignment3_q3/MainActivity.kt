package com.example.assignment3_q3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import com.example.assignment3_q3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val temperatureViewModel : TemperatureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val celsius = temperatureViewModel.celsius
        updateMessage(celsius)
        updateCelciusNumber(celsius)
        updateFahrenheitNumber(celsiusToFahrenheit(celsius))

        binding.celsiusSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val celsius = progress
                    val fahrenheit = celsiusToFahrenheit(celsius)
                    binding.fahrenheitSeekbar.progress = fahrenheit

                    updateMessage(progress)
                    updateCelciusNumber(celsius)
                    updateFahrenheitNumber(fahrenheit)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun celsiusToFahrenheit(celsius: Int): Int {
        return ((celsius * 9 / 5) + 32)
    }

    private fun fahrenheitToCelsius(fahrenheit: Int): Int {
        return ((fahrenheit - 32) * 5 / 9)
    }

    private fun updateMessage(celsius: Int) {
        binding.interestingMessage.text = if (celsius <= 20) "I wish it were warmer." else "I wish it were colder."
    }

    private fun updateCelciusNumber(celsius: Int) {
        temperatureViewModel.celsius = celsius
        binding.celsiusNumber.text = celsius.toString()
    }

    private fun updateFahrenheitNumber(fahrenheit: Int) {
        binding.fahrenheitNumber.text = fahrenheit.toString()
    }
}
