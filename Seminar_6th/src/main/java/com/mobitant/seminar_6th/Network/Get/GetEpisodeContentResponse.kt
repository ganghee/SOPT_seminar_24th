package com.mobitant.seminar_6th.Network.Get

import com.mobitant.seminar_6th.Data.EpisodeContentData

data class GetEpisodeContentResponse(
    val status : Int,
    val success: Boolean,
    val message: String,
    val data: EpisodeContentData?
)