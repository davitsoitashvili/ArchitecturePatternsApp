package com.example.architecturepatternsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturepatternsapp.adapters.CommentsAdapter
import com.example.architecturepatternsapp.databinding.ActivityCommentsBinding
import com.example.architecturepatternsapp.ui.vm.CommentsVm
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
        commentsVm.makeCommentsApiCall()
    }

    private fun init() {
        initCommentAdapter()
        initObservers()
    }

    private fun initCommentAdapter() {
        commentsAdapter = CommentsAdapter(mutableListOf())
        binding.commentsRecyclerView.adapter = commentsAdapter
        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initObservers() {
        commentsVm.getComments().observe(this) {
            commentsAdapter.updateComments(it)
        }

        commentsVm.getErrorMessage().observe(this) {
            showErrorMessage(it)
        }
    }

    private fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}