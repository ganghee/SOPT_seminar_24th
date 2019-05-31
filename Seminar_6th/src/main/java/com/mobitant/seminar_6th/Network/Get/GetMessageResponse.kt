package com.mobitant.seminar_6th.Network.Get

data class GetMessageResponse (
    val status : Int,
    val success: Boolean,
    val message: String
)