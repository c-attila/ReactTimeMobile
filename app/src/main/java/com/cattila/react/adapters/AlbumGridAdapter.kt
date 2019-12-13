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

package com.cattila.react.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cattila.react.databinding.GridViewItemBinding
import com.cattila.react.network.AlbumProperty

class AlbumGridAdapter : ListAdapter<AlbumProperty, AlbumGridAdapter.AlbumPropertyViewHolder>(DiffCallback) {

    class AlbumPropertyViewHolder(private var binding: GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(albumProperty: AlbumProperty) {
            binding.property = albumProperty
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<AlbumProperty>() {
        override fun areItemsTheSame(oldItem: AlbumProperty, newItem: AlbumProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AlbumProperty, newItem: AlbumProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AlbumPropertyViewHolder {
        return AlbumPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AlbumPropertyViewHolder, position: Int) {
        val albumProperty = getItem(position)
        holder.bind(albumProperty)
    }
}
