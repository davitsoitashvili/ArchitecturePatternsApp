package com.example.architecturepatternsapp.presenter

import com.example.architecturepatternsapp.ui.CommentsActivityCallback
import com.example.model.datasources.CommentDataSource
import com.example.model.models.Comment
import com.example.model.models.GeneralResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Davit Soitashvili on 08.01.22
 **/

class CommentsPresenterImpl(private val commentDataSource: CommentDataSource) : CommentsPresenter {

    private var commentActivityCallback: CommentsActivityCallback<List<Comment>>? = null

    override fun initView(commentActivityCallback: CommentsActivityCallback<List<Comment>>) {
        this.commentActivityCallback = commentActivityCallback
    }

    override fun makeCommentsApiCall() {
        CoroutineScope(Dispatchers.IO).launch {
            when (val response = commentDataSource.getComments()) {
                is GeneralResponse.Success -> commentActivityCallback?.viewStateCallback(
                    getCommentsWithEventId(response.result)
                )
                is GeneralResponse.Error -> commentActivityCallback?.errorCallback(response.errorMessage)
            }
        }
    }

    private fun getCommentsWithEventId(comments: List<Comment>): List<Comment> {
        val commentsWithEvenId = mutableListOf<Comment>()
        comments.forEach {
            if (isCommentIdEven(it)) {
                commentsWithEvenId.add(it)
            }
        }
        return commentsWithEvenId
    }

    override fun clearView() {
        commentActivityCallback = null
    }

    private fun isCommentIdEven(comment: Comment) = comment.id.toInt() % 2 == 0
}