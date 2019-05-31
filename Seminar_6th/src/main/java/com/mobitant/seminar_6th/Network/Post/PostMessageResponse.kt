package com.mobitant.seminar_6th.Network.Post

data class PostMessageResponse (
    val status : Int,
    val success: Boolean,
    val message: String
)