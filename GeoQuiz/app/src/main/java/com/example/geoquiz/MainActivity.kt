package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quizViewModel : QuizViewModel by viewModels()

    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if(result.resultCode == RESULT_OK) {
            quizViewModel.isCheater = result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(savedInstanceState: Bundle?) called...")
//        setContentView(R.layout.activity_main)
//        trueButton = findViewById(R.id.true_button)
//        falseButton = findViewById(R.id.false_button)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cheatButton.setOnClickListener {
            val intent = CheatActivity.createNewIntent(this, answerIsTrue = quizViewModel.currentQuestionAnswer)
            cheatLauncher.launch(intent) // start the CheatActivity using intent as "argument". Actually ActivityManager handle this
        }
        binding.trueButton.setOnClickListener { view: View ->
            this.checkAnswer(true)
        }
        binding.falseButton.setOnClickListener { view: View ->
            this.checkAnswer(false)
        }
        binding.previousButton.setOnClickListener { view: View ->
            quizViewModel.moveQuestion(-1)
            quizViewModel.isCheater = false
            this.updateQuestion()
        }
        binding.nextButton.setOnClickListener { view: View ->
            quizViewModel.moveQuestion(+1)
            quizViewModel.isCheater = false
            this.updateQuestion()
        }
        this.updateQuestion()
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


    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when { // switch
            quizViewModel.isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Snackbar.make(binding.trueOrFalseLayout, getText(messageResId), Snackbar.LENGTH_SHORT).show()
    }
}

