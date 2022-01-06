package com.example.model.datasources

import com.example.model.apiservice.comment.CommentApiService
import com.example.model.models.Comment
import com.example.model.models.GeneralResponse

/**
 * Created by Davit Soitashvili on 06.01.22
 **/

class CommentDataSourceImpl(private val commentApiService: CommentApiService) : CommentDataSource {
    override suspend fun getComments(): GeneralResponse<List<Comment>> {
        val response = commentApiService.getComments()
        if (!response.isSuccessful) {
            return GeneralResponse.Error(response.message())
        }
        return GeneralResponse.Success(response.body()!!)
    }
}