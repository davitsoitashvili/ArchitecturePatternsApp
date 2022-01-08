package com.example.architecturepatternsapp.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturepatternsapp.actions.CommentsAction
import com.example.model.states.CommentsState
import com.example.model.datasources.CommentDataSource
import com.example.model.models.Comment
import com.example.model.models.GeneralResponse
import kotlinx.coroutines.launch

/**
 * Created by Davit Soitashvili on 08.01.22
 **/

class CommentsVm(private val commentDataSource: CommentDataSource) : ViewModel() {

    private val commentsState: MutableLiveData<CommentsState> = MutableLiveData()

    fun getCommentsState(): LiveData<CommentsState> = commentsState

    fun sendAction(action: CommentsAction) {
        when (action) {
            is CommentsAction.ReceiveComments -> makeCommentsApiCall()
        }
    }

    private fun makeCommentsApiCall() {
        viewModelScope.launch {
            when (val response = commentDataSource.getComments()) {
                is GeneralResponse.Success -> commentsState.value =
                    CommentsState.OnCommentsState(getCommentsWithEventId(response.result))
                is GeneralResponse.Error -> commentsState.value =
                    CommentsState.OnErrorState(response.errorMessage)
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