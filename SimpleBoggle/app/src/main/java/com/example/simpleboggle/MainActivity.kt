package com.example.simpleboggle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpleboggle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wordFragment = WordFragment.newInstance()
        val dashboardFragment = DashboardFragment.newInstance()

        wordFragment.communicator = dashboardFragment
        dashboardFragment.communicator = wordFragment

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.wordFragmentContainer, wordFragment)
        }.commit()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.dashboardFragmentContainer, dashboardFragment)
        }.commit()
    }

    interface FragmentCommunicatorWord2Dashboard {
        fun onScoreSent (score: Int);
    }

    interface FragmentCommunicatorDashboard2Word {
        fun onNewGameClicked();
    }
}