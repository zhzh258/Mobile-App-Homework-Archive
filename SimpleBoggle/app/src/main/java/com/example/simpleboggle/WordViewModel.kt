package com.example.simpleboggle

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WordViewModel: ViewModel() {
    public val letters: Array<Array<String>> = Array(4) { Array(4) { "" } }
    public val selected: Array<Array<Boolean>> = Array(4) { Array(4) { false } }
    public var currentRow: Int = -1
    public var currentCol: Int = -1
    public val currentWord: MutableLiveData<String> = MutableLiveData<String>("")
    private val hashing: HashSet<String> = hashSetOf()

    init {
        Log.d("MY_DEBUG", "loading wordViewModel...")
    }

    fun loadHashset(context: Context) {
        context.resources.openRawResource(R.raw.words).bufferedReader().use { reader ->
            reader.forEachLine { line ->
                val word = line.trim()
                hashing.add(word)
            }
        }
    }

    fun isWordInSet(word: String): Boolean {
        return word in hashing || word.lowercase() in hashing;
    }

    fun resetData() {
        selected.forEachIndexed { i, row ->
            row.forEachIndexed { j, _ ->
                selected[i][j] = false;
            }
        }
        currentRow = -1
        currentCol = -1
        currentWord.value = ""
    }

}