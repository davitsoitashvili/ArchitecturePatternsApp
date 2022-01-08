package com.example.model.states

import com.example.model.models.Comment


/**
 * Created by Davit Soitashvili on 08.01.22
 **/

sealed class CommentsState {
    class OnCommentsState(val comments: List<Comment>) : CommentsState()
    class OnError(val errorMessage : String) : CommentsState()
    // other states ...
}