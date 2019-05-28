package com.mobitant.seminar_6th.Network.Get

data class GetMainTopImageResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<String>?
)
