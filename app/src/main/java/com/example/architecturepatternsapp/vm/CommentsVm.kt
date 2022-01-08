package com.example.architecturepatternsapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.datasources.CommentDataSource
import com.example.model.models.Comment
import com.example.model.models.GeneralResponse
import kotlinx.coroutines.launch

/**
 * Created by Davit Soitashvili on 08.01.22
 **/

class CommentsVm(private val commentDataSource: CommentDataSource) : ViewModel() {

    private val comments: MutableLiveData<List<Comment>> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getComments(): LiveData<List<Comment>> = comments

    fun getErrorMessage(): LiveData<String> = errorMessage

    fun makeCommentsApiCall() {
        viewModelScope.launch {
            when (val response = commentDataSource.getComments()) {
                is GeneralResponse.Success -> comments.value =
                    getCommentsWithEventId(response.result)
                is GeneralResponse.Error -> errorMessage.value = response.errorMessage
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

    private fun isCommentIdEven(comment: Comment) = comment.id.toInt() % 2 == 0
}