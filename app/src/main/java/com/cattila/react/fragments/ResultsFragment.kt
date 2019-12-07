package com.cattila.react.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.cattila.react.R
import com.cattila.react.data.ResultDatabase
import com.cattila.react.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {

    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val db = Room.databaseBuilder(
            activity!!.applicationContext,
            ResultDatabase::class.java, "database"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_results, container, false)

        binding.resultsButton1.setOnClickListener {
            findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToMenuFragment())
        }

        var results = db.resultDao().getTopTen()
        println(results.toString())
        binding.textView.text = results.toString()

        return binding.root
    }
}
