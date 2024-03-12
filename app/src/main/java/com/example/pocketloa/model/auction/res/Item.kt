package com.example.pocketloa.model.auction.res

data class Item(
    val AuctionInfo: AuctionInfo,
    val Grade: String, // 등급
    val GradeQuality: Int, // 품질
    val Icon: String,
    val Level: Any,
    val Name: String,
    val Options: List<Option>,
    val Tier: Int // 티어
)