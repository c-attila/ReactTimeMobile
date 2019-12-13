package com.cattila.react

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cattila.react.adapters.AlbumGridAdapter
import com.cattila.react.network.AlbumProperty

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<AlbumProperty>?) {
    val adapter = recyclerView.adapter as AlbumGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("albumUserId")
fun TextView.setAlbumUserId(item: AlbumProperty) {
    text = item.userId.toString()
}

@BindingAdapter("albumId")
fun TextView.setAlbumId(item: AlbumProperty) {
    text = item.id.toString()
}

@BindingAdapter("albumTitle")
fun TextView.setAlbumTitle(item: AlbumProperty) {
    text = item.title
}