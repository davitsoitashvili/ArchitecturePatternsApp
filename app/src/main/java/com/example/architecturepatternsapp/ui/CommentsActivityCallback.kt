package com.example.architecturepatternsapp.ui

/**
* Created by Davit Soitashvili on 08.01.22
**/

interface CommentsActivityCallback<T> {
    suspend fun viewStateCallback(result : T)
    suspend fun errorCallback(errorMessage : String)
}