package com.cattila.react.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cattila.react.R
import com.cattila.react.data.Result
import kotlinx.android.synthetic.main.result_item.view.*

class ResultAdapter : ListAdapter<Result, ResultAdapter.ResultHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.playerName == newItem.playerName && oldItem.elapsed == newItem.elapsed
                        && oldItem.date == newItem.date
            }
        }
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.result_item, parent, false)
        return ResultHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        val currentResult: Result = getItem(position)

        holder.textViewTitle.text = currentResult.playerName
        holder.textViewPriority.text = currentResult.date.toString()
        holder.textViewDescription.text = currentResult.elapsed.toString()
    }

    fun getResultAt(position: Int): Result {
        return getItem(position)
    }

    inner class ResultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        var textViewTitle: TextView = itemView.text_view_playerName
        var textViewPriority: TextView = itemView.text_view_date
        var textViewDescription: TextView = itemView.text_view_elapsed
    }

    interface OnItemClickListener {
        fun onItemClick(result: Result)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
