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
import com.cattila.react.databinding.FragmentNameBinding

class NameFragment : Fragment() {

    lateinit var binding: FragmentNameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)

        binding.button.setOnClickListener {
            findNavController().navigate(NameFragmentDirections.actionNameFragmentToGameFragment(binding.textInput.text.toString()))
        }

        return binding.root
    }

}
