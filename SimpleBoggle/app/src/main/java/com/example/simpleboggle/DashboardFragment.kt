package com.example.simpleboggle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.simpleboggle.databinding.FragmentDashboardBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment(), MainActivity.FragmentCommunicatorWord2Dashboard {
    private lateinit var binding: FragmentDashboardBinding
    private val dashboardViewModel: DashboardViewModel by viewModels()
    var communicator: MainActivity.FragmentCommunicatorDashboard2Word? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        dashboardViewModel.score.observe(viewLifecycleOwner) {
            binding.scoreTextView.text = getString(R.string.score, it)
        }
        binding.newGameButton.setOnClickListener {
            dashboardViewModel.score.value = 0
            Toast.makeText(context, "You started a new game! The score is reset to 0", Toast.LENGTH_SHORT).show()
            communicator?.onNewGameClicked()
        }
        return binding.root
    }

    override fun onScoreSent(score: Int) {
        val prevScore = dashboardViewModel.score.value ?: 0
        dashboardViewModel.score.value = prevScore + score
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): DashboardFragment {
            Log.d("MY_DEBUG", "DashboardFragment instance created")
            return DashboardFragment()
        }
    }
}