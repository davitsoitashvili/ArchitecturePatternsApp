package com.example.model.models

/**
 * Created by Davit Soitashvili on 06.01.22
 **/

data class Comment(
    val postId : String,
    val name : String,
    val email : String,
    val body : String
)