package com.mobitant.seminar_6th.Network.Get

import com.mobitant.seminar_6th.Data.CommentData

data class GetCommentReadResponse (
    val status : Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<CommentData>?
)