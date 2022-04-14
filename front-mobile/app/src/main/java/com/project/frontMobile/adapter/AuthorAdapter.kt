package com.project.frontMobile.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.project.frontMobile.R
import com.project.frontMobile.data.model.Author
import com.project.frontMobile.ui.book.BookFragmentDirections

class AuthorAdapter(private val context: Context, private val dataset: List<Author>): RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_author, parent, false)

        return AuthorViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val author = dataset[position]

        holder.authorName.text = context.resources.getString(R.string.author_name, author.firstName, author.name)

        val base64: ByteArray = Base64.decode(author.base64, Base64.DEFAULT)
        holder.profilePicture.setImageBitmap(BitmapFactory.decodeByteArray(base64, 0, base64.size))

        holder.mainContainer.setOnClickListener { view ->
            val action = BookFragmentDirections.actionBookFragmentToAuthorFragment(author.id)
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = dataset.size

    class AuthorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val authorName: TextView = view.findViewById(R.id.author_name)
        val profilePicture: ImageView = view.findViewById(R.id.profile_picture)
        val mainContainer: ConstraintLayout = view.findViewById(R.id.mainContainer)
    }
}