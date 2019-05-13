package com.mobitant.seminar_3rd.Data

//에피소드 목록 화면의 데이터
data class EpisodeOverviewData (
    var product_id:Int,
    var episode_id:Int,
    var img_url:String,
    var title: String,
    var num_view: Int,
    var publish_date:String
)