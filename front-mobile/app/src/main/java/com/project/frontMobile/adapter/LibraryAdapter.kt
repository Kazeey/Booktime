package com.project.frontMobile.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.databinding.ItemBookBinding
import com.project.frontMobile.databinding.ItemHeaderBinding

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class LibraryAdapter(private val clickListener: LibraryListener): ListAdapter<LibraryAdapter.DataItem, RecyclerView.ViewHolder>(LibraryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    fun addHeaderAndSubmitList(headerList: List<String>, bookList: List<Book>) {
        val data = mutableListOf<DataItem>()

        for (header: String in headerList) {
            data.addAll(createSection(header, bookList))
        }

        submitList(data)
    }

    private fun createSection(header: String, bookList: List<Book>): List<DataItem> {
        val dataItemList = mutableListOf<DataItem>()

        dataItemList.add(DataItem.Header(header))

        for (book: Book in bookList) {
            if (book.title.first().uppercase() == header) {
                dataItemList.add(DataItem.BookItem(book))
            }
        }

        return dataItemList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val bookItem = getItem(position) as DataItem.BookItem
                holder.bind(bookItem.book, clickListener)
            }
            is HeaderViewHolder -> {
                val item = getItem(position) as DataItem.Header
                holder.bind(item.content)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.BookItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class HeaderViewHolder private constructor(private val binding: ItemHeaderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.content = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHeaderBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

    class ViewHolder private constructor(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book, clickListener: LibraryListener) {
            binding.book = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBookBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class LibraryDiffCallback: DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    class LibraryListener(val clickListener: (book: Book) -> Unit) {
        fun onClick(book: Book) = clickListener(book)
    }

    sealed class DataItem {

        abstract val id: String

        data class BookItem(val book: Book): DataItem() {
            override val id = book.id
        }

        data class Header(val content: String): DataItem() {
            override val id = Long.MIN_VALUE.toString()
        }
    }
}