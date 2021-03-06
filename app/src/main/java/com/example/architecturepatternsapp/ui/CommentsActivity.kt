package com.example.architecturepatternsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturepatternsapp.adapters.CommentsAdapter
import com.example.architecturepatternsapp.databinding.ActivityCommentsBinding
import com.example.model.datasources.CommentDataSource
import com.example.model.models.Comment
import com.example.model.models.GeneralResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class CommentsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCommentsBinding

    private lateinit var commentsAdapter: CommentsAdapter

    private val commentDataSource : CommentDataSource by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCommentAdapter()
        makeCommentsApiCall()
    }

    private fun initCommentAdapter() {
        commentsAdapter = CommentsAdapter(mutableListOf())
        binding.commentsRecyclerView.adapter = commentsAdapter
        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun makeCommentsApiCall() {
        CoroutineScope(Dispatchers.IO).launch {
            when(val response = commentDataSource.getComments()) {
                is GeneralResponse.Success -> {
                    withContext(Dispatchers.Main) {
                        commentsAdapter.updateComments(getCommentsWithEventId(response.result))
                    }
                }
                is GeneralResponse.Error -> showErrorMessage(response.errorMessage)
            }
        }
    }

    private fun getCommentsWithEventId(comments : List<Comment>) : List<Comment> {
        val commentsWithEvenId = mutableListOf<Comment>()
        comments.forEach {
            if (isCommentIdEven(it)) {
                commentsWithEvenId.add(it)
            }
        }
        return commentsWithEvenId
    }

    private fun isCommentIdEven(comment : Comment) = comment.id.toInt() % 2 == 0

    private fun showErrorMessage(errorMessage : String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}