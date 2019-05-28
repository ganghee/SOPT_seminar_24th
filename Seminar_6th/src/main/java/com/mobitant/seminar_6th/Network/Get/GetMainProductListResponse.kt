package com.mobitant.seminar_6th.Network.Get

import com.mobitant.seminar_6th.Data.ProductOverviewData

data class GetMainProductListResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<ProductOverviewData>?
)