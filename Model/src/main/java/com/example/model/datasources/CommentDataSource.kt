package com.example.model.datasources

import com.example.model.models.Comment
import com.example.model.models.GeneralResponse
import retrofit2.Response

/**
 * Created by Davit Soitashvili on 06.01.22
 **/

interface CommentDataSource {
    suspend fun getComments() : GeneralResponse<List<Comment>>
}