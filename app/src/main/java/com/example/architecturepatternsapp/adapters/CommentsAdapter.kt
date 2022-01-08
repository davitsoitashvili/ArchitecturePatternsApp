package com.example.architecturepatternsapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturepatternsapp.databinding.CommentRecyclerItemBinding
import com.example.model.models.Comment

/**
 * Created by Davit Soitashvili on 06.01.22
 **/

class CommentsAdapter(private var comments: List<Comment>) :
    RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        CommentViewHolder(
            CommentRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.onBindComment(comments[position])
    }

    override fun getItemCount() = comments.size

    class CommentViewHolder(private val binding: CommentRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBindComment(comment: Comment) {
            with(binding) {
                commentIdTextView.text = "Comment ID : ${comment.id}"
                commentNameTextView.text = "Comment Name : ${comment.name}"
                commentEmailTextView.text = "Comment Email : ${comment.email}"
                commentBodyTextView.text = "Comment Body : ${comment.body}"
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateComments(comments: List<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }
}