package com.example.model.datasources

import com.example.model.apiservice.comment.CommentApiService
import com.example.model.models.Comment
import retrofit2.Response

/**
 * Created by Davit Soitashvili on 06.01.22
 **/

class CommentDataSourceImpl(private val commentApiService: CommentApiService) : CommentDataSource {
    override suspend fun getComments(): Response<List<Comment>> {
        return commentApiService.getComments()
    }
}