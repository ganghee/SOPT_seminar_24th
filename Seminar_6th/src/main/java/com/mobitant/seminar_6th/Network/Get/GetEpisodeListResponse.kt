package com.mobitant.seminar_6th.Network.Get

import com.mobitant.seminar_6th.Data.EpisodeListData

data class GetEpisodeListResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: EpisodeListData?
)