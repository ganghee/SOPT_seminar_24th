package com.mobitant.seminar_4th.Data

//댓글 달기 화면의 데이터
data class CommentData(
    var product_id: Int,
    var episode_id: Int,
    var comment_id: Int,
    var img_url: String,
    var author: String,
    var comment: String,
    var publish_date: String
)