package com.example.pocketloa.model.auction.res

data class AuctionInfo(
    val BidCount: Int,
    val BidPrice: Int,
    val BidStartPrice: Int,
    val BuyPrice: Int,
    val EndDate: String,
    val IsCompetitive: Boolean,
    val StartPrice: Int,
    val TradeAllowCount: Int
)