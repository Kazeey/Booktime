package com.project.frontMobile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.frontMobile.databinding.ItemIndexBinding

class IndexAdapter: ListAdapter<String, IndexAdapter.IndexViewHolder>(IndexDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexViewHolder {
        return IndexViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IndexViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class IndexViewHolder(val binding: ItemIndexBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.index = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): IndexViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemIndexBinding.inflate(layoutInflater, parent, false)
                return IndexViewHolder(binding)
            }
        }
    }

    class IndexDiffCallback: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
}