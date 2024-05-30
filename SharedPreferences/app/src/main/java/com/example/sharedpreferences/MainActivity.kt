package com.example.sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sharedpreferences.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    private lateinit var sharedPreferences: SharedPreferences;
    private var text: String = "";
    private var imageIndex: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);

        Log.d("MYDEBUG", sharedPreferences.getString("text_in_EditText", "").toString())
        Log.d("MYDEBUG",sharedPreferences.getInt("index_of_ImageView", 0).toString())
        text = sharedPreferences.getString("text_in_EditText", "").toString();
        imageIndex = sharedPreferences.getInt("index_of_ImageView", 0);


        binding.editText.setText(text)
        binding.imageView.setImageResource(getImageResource(imageIndex))

        binding.button.setOnClickListener {
            imageIndex = Random.nextInt(0, 4); // 0, 1, 2
            binding.imageView.setImageResource(getImageResource(imageIndex))
        }
    }
    // it doesn't work using onDestroy(). Because sharedPreferences.edit().apply..." in onDestroy() is not guaranteed to be called
    override fun onPause() {
        super.onPause()
        Log.d("MYDEBUG", "ondestroy...")
        sharedPreferences.edit().apply {
            putInt("index_of_ImageView", imageIndex)
            Log.d("MYDEBUG", imageIndex.toString())
            putString("text_in_EditText", binding.editText.text.toString())
            Log.d("MYDEBUG", binding.editText.text.toString())
            apply()
        }
        Log.d("MYDEBUG", sharedPreferences.getString("text_in_EditText", "").toString())
        Log.d("MYDEBUG",sharedPreferences.getInt("index_of_ImageView", 0).toString())
    }

    private fun getImageResource(index: Int): Int {
        return when(index) {
            0 -> R.drawable.image0
            1 -> R.drawable.image1
            2 -> R.drawable.image2
            3 -> R.drawable.image3
            else -> R.drawable.image0
        }
    }

}