package com.example.model.models

/**
 * Created by Davit Soitashvili on 06.01.22
 **/

sealed class GeneralResponse<R> {
    class Success<R>(val result : R) : GeneralResponse<R>()
    class Error<R>(val errorMessage : String) : GeneralResponse<R>()
}