package com.example.model.apiservice.comment

import com.example.model.models.Comment
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Davit Soitashvili on 06.01.22
 **/

interface CommentApiService {
    @GET("comments")
    suspend fun getComments() : Response<List<Comment>>
}