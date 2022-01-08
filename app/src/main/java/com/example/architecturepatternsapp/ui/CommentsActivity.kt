package com.example.architecturepatternsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturepatternsapp.actions.CommentsAction
import com.example.architecturepatternsapp.adapters.CommentsAdapter
import com.example.architecturepatternsapp.databinding.ActivityCommentsBinding
import com.example.model.states.CommentsState
import com.example.architecturepatternsapp.ui.vm.CommentsVm
import com.example.model.models.Comment
import org.koin.android.ext.android.inject

class CommentsActivity : AppCompatActivity() {

    private val commentsVm: CommentsVm by inject()

    private lateinit var binding: ActivityCommentsBinding

    private lateinit var commentsAdapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        commentsVm.sendAction(CommentsAction.ReceiveComments)
    }

    private fun init() {
        initCommentAdapter()
        onHandleState()
    }

    private fun initCommentAdapter() {
        commentsAdapter = CommentsAdapter(mutableListOf())
        binding.commentsRecyclerView.adapter = commentsAdapter
        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    // single place for handling "CommentsActivity" state
    private fun onHandleState() {
        commentsVm.getCommentsState().observe(this) {
            when (it) {
                is CommentsState.OnCommentsState -> updateCommentsAdapter(it.comments)
                is CommentsState.OnErrorState -> showErrorMessage(it.errorMessage)
            }
        }
    }

    private fun updateCommentsAdapter(comments: List<Comment>) {
        commentsAdapter.updateComments(comments)
    }

    private fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}