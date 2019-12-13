package com.cattila.react.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.cattila.react.R
import com.cattila.react.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_menu, container, false)

        binding.menuButton1.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToNameFragment())
        }

        binding.menuButton2.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToResultsFragment())
        }

        binding.menuButton3.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToOverviewFragment())
        }

        return binding.root
    }
}