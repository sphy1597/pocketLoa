package com.example.pocketloa.model.auction.res

data class AuctionResponse(
    val PageNo: Int,
    val PageSize: Int,
    val TotalCount: Int,
    val Items: List<Item>?,

)