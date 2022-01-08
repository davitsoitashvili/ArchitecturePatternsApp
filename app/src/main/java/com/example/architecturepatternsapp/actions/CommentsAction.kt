package com.example.architecturepatternsapp.actions

/**
 * Created by Davit Soitashvili on 08.01.22
 **/

sealed class CommentsAction {
    object ReceiveComments : CommentsAction()
    // other actions ...
}