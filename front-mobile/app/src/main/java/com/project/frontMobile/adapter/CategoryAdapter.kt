package com.project.frontMobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.project.frontMobile.R

class CategoryAdapter(private val context: Context, private val dataset: List<String>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)

        return CategoryViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val label = dataset[position]

        holder.chip.text = label
    }

    override fun getItemCount() = dataset.size

    class CategoryViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val chip = view.findViewById<Chip>(R.id.category)
    }
}