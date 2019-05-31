package com.mobitant.seminar_6th.Network.Put

data class PutMessageResponse (
    val status : Int,
    val success: Boolean,
    val message: String
)