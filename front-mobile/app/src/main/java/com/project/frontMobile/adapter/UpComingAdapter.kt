package com.project.frontMobile.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.frontMobile.R
import com.project.frontMobile.adapter.UpComingAdapter.UpComingViewHolder
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.databinding.ItemBookComingSoonBinding

class UpComingAdapter(val clickListener: UpComingListener): ListAdapter<Book, UpComingViewHolder>(UpComingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder {
        return UpComingViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class UpComingViewHolder private constructor(val binding: ItemBookComingSoonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book, clickListener: UpComingListener) {
            binding.book = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): UpComingViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBookComingSoonBinding.inflate(layoutInflater, parent, false)
                return UpComingViewHolder(binding)
            }
        }
    }

    class UpComingDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

    }

    class UpComingListener(val clickListener: (bookId: String) -> Unit) {
        fun onClick(book: Book) = clickListener(book.id)
    }
}