package com.example.simpleboggle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.viewModels
import com.example.simpleboggle.databinding.FragmentWordBinding
import kotlin.math.abs

/**
 * A simple [Fragment] subclass.
 * Use the [WordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordFragment : Fragment(), MainActivity.FragmentCommunicatorDashboard2Word {
    private lateinit var binding: FragmentWordBinding
    private val wordViewModel: WordViewModel by viewModels()
    private val totalButtons = 16
    var communicator: MainActivity.FragmentCommunicatorWord2Dashboard? = null // sender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordBinding.inflate(layoutInflater)
        wordViewModel.currentWord.observe(viewLifecycleOwner) { it ->
            binding.currentWordTextView.text = getString(R.string.word, it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* Set up buttons UI */
        for (i in 0 until totalButtons) {
            val row = i / 4
            val col = i % 4
            val button = Button(context).apply {
                text = generateRandomLetter().let { it -> wordViewModel.letters[row][col] = it; it }
                id = View.generateViewId()
                setOnClickListener {
                    handleLetterButtonClick(it as Button, row, col);
                }
                setBackgroundColor(resources.getColor(android.R.color.white))
            }
            val params = GridLayout.LayoutParams().apply {
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                width = 0 // Use this with weights for equal distribution
                height = GridLayout.LayoutParams.WRAP_CONTENT
                bottomMargin = 10
                topMargin = 10
                leftMargin = 10
                rightMargin = 10
            }
            button.layoutParams = params
            binding.wordGridLayout.addView(button)
        }
        /* Load the word hashset*/
        wordViewModel.loadHashset(requireContext())
        /* Set up the SUBMIT button and the CLEAR button */
        binding.submitButton.setOnClickListener {
            handleSubmitButtonClick()
        }
        binding.clearButton.setOnClickListener {
            handleClearButtonClick()
        }
    }

    private fun generateRandomLetter(): String {
        val chars = ('A'..'Z')
        return chars.random().toString()
    }

    private fun handleLetterButtonClick(button: Button, row: Int, col: Int){
        val validChoice: Boolean = wordViewModel.currentWord.value?.length == 0 ||
                (abs(row - wordViewModel.currentRow) <= 1 && abs(col - wordViewModel.currentCol) <= 1)
        if(!validChoice){
            Toast.makeText(context, "Invalid choice! You may only select connected letters", Toast.LENGTH_SHORT).show()
            return
        }
        if(wordViewModel.currentWord.value!!.length > 0){
            val previousButton = binding.wordGridLayout.getChildAt(wordViewModel.currentRow * 4 + wordViewModel.currentCol)
            previousButton.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))
        }

        button.isEnabled = false;
        wordViewModel.selected[row][col] = true;
        wordViewModel.currentWord.value = wordViewModel.currentWord.value + button.text
        wordViewModel.currentRow = row;
        wordViewModel.currentCol = col;
        button.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
    }

    private fun handleSubmitButtonClick() {
        val score = calcScore(wordViewModel.currentWord.value ?: "")
        // send the score
        communicator?.onScoreSent(score)
        // reset the game
        wordViewModel.resetData();
        for (i in 0 until totalButtons) {
            val row = i / 4
            val col = i % 4
            binding.wordGridLayout.getChildAt(i).apply {
                this as Button
                text = generateRandomLetter().let { it -> wordViewModel.letters[row][col] = it; it }
                isEnabled = true
                setBackgroundColor(resources.getColor(android.R.color.white))
            }
        }
    }

    private fun calcScore(word: String): Int {
        if (word.length < 4 || countVowel(word) < 2 || !wordViewModel.isWordInSet(word)) {
            Toast.makeText(context, "That’s incorrect, -10", Toast.LENGTH_SHORT).show()
            return -10;
        } else {
            var baseScore = 0
            var rate = 1
            Toast.makeText(context, "Valid word", Toast.LENGTH_SHORT).show()
            word.forEach { ch ->
                if(ch in setOf<Char>('S', 'Z', 'P', 'X', 'Q')){
                    rate *= 2
                }
                if (ch in setOf<Char>('A', 'E', 'I', 'O', 'U')) { // vowel
                    baseScore += 5
                } else { // consonant
                    baseScore += 1
                }
            }
            Toast.makeText(context, "That’s correct, +${baseScore * rate}", Toast.LENGTH_SHORT).show()
            return baseScore * rate
        }
    }

    private fun countVowel(word: String): Int {
        return word.count{ ch -> ch in setOf<Char>('A', 'E', 'I', 'O', 'U') }
    }

    private fun handleClearButtonClick() {
        // reset the input
        wordViewModel.resetData();
        for (i in 0 until totalButtons) {
            binding.wordGridLayout.getChildAt(i).apply {
                this as Button
                isEnabled = true
                setBackgroundColor(resources.getColor(android.R.color.white))
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment WordFragment.
         */
        @JvmStatic
        fun newInstance(): WordFragment {
            Log.d("MY_DEBUG", "WordFragment instance created")
            return WordFragment()
        }
    }

    override fun onNewGameClicked() {
        wordViewModel.resetData();
        for (i in 0 until totalButtons) {
            val row = i / 4
            val col = i % 4
            binding.wordGridLayout.getChildAt(i).apply {
                this as Button
                text = generateRandomLetter().let { it -> wordViewModel.letters[row][col] = it; it }
                isEnabled = true
                setBackgroundColor(resources.getColor(android.R.color.white))
            }
        }
    }
}