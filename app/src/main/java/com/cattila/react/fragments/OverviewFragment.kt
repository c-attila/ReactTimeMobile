/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.cattila.react.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.cattila.react.R
import com.cattila.react.adapters.AlbumGridAdapter
import com.cattila.react.databinding.FragmentOverviewBinding
import com.cattila.react.viewmodels.OverviewViewModel

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        binding.albumGrid.adapter = AlbumGridAdapter()

        setHasOptionsMenu(true)
        return binding.root
    }
}
