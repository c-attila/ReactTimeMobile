package com.cattila.react.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import com.cattila.react.R
import com.cattila.react.databinding.FragmentGameBinding
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random
import kotlin.random.nextInt

class GameFragment : Fragment() {

    lateinit var binding: FragmentGameBinding
    var elapsed = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false
        )

        binding.gameButton1.setOnClickListener {
            println(String.format("%d ms", System.currentTimeMillis() - elapsed))
            Toast.makeText(
                context,
                String.format("%d ms", System.currentTimeMillis() - elapsed),
                Toast.LENGTH_SHORT
            ).show()
            elapsed = 0L
        }

        var switch = true
        binding.gameButton2.setOnClickListener {

            if (switch) {
                var rand = Random.nextLong(500, 3000)
                println(rand)
                Thread.sleep(rand)
                binding.gameButton1.visibility = View.VISIBLE
                elapsed = System.currentTimeMillis()
                switch = false
            } else {
                binding.gameButton1.visibility = View.GONE
                switch = true
            }

        }

        return binding.root
    }
}
