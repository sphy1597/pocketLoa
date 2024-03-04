package com.example.pocketloa.model.auction.res

data class Item(
    val AuctionInfo: AuctionInfo,
    val Grade: String,
    val GradeQuality: Int,
    val Icon: String,
    val Level: Any,
    val Name: String,
    val Options: List<Option>,
    val Tier: Int
)