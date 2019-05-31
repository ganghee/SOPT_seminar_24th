package com.mobitant.seminar_6th.Data

//댓글 리스트 화면의 데이터
data class CommentData(
    var idx: Int,
    var cmtImg: String,
    var name: String,
    var content: String,
    var writetime: String
)