package com.example.architecturepatternsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturepatternsapp.adapters.CommentsAdapter
import com.example.architecturepatternsapp.databinding.ActivityCommentsBinding
import com.example.architecturepatternsapp.presenter.CommentsPresenter
import com.example.model.models.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class CommentsActivity : AppCompatActivity(), CommentsActivityCallback<List<Comment>> {

    private val commentsPresenter: CommentsPresenter by inject()

    private lateinit var binding: ActivityCommentsBinding

    private lateinit var commentsAdapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        commentsPresenter.makeCommentsApiCall()
    }

    private fun init() {
        initCommentAdapter()
        commentsPresenter.initView(this)
    }

    private fun initCommentAdapter() {
        commentsAdapter = CommentsAdapter(mutableListOf())
        binding.commentsRecyclerView.adapter = commentsAdapter
        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override suspend fun viewStateCallback(result: List<Comment>) {
        withContext(Dispatchers.Main) {
            commentsAdapter.updateComments(result)
        }
    }

    override suspend fun errorCallback(errorMessage: String) {
        showToastMessage(errorMessage)
    }

    override fun onDestroy() {
        commentsPresenter.clearView()
        super.onDestroy()
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}