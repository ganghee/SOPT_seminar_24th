package com.mobitant.seminar_4th.Network.Get

import com.mobitant.seminar_4th.Data.ProductOverviewData

data class GetMainProductListResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<ProductOverviewData>?
)