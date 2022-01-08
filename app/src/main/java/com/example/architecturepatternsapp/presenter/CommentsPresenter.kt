package com.example.architecturepatternsapp.presenter

import com.example.architecturepatternsapp.ui.CommentsActivityCallback
import com.example.model.models.Comment

/**
 * Created by Davit Soitashvili on 08.01.22
 **/

interface CommentsPresenter {
    fun makeCommentsApiCall()
    fun clearView()
    fun initView(commentActivityCallback: CommentsActivityCallback<List<Comment>>)
}