package com.example.pocketloa.model.auction.res

data class auctionResponse(
    val Items: List<Item>,
    val PageNo: Int,
    val PageSize: Int,
    val TotalCount: Int
)