package com.mobitant.seminar_6th.Data

data class EpisodeListData (
    var webtoonInfo: WebtoonInfo,
    var list: ArrayList<EpisodeOverviewData>,
    var isLiked:Boolean
)