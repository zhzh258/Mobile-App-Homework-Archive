package com.example.simpleboggle

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel: ViewModel() {
    public var score: MutableLiveData<Int> = MutableLiveData<Int>(0)

    init {
        Log.d("MY_DEBUG", "loading dashboardViewModel...")
    }
}