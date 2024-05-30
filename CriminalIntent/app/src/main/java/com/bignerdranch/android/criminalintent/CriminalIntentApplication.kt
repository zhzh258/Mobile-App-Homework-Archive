package com.bignerdranch.android.criminalintent

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.bignerdranch.android.criminalintent.database.CrimeDatabase

class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}
