package com.example.pocketloa.network.model.auction

data class AuctionInfo(
	val startPrice: Int,
	val buyPrice: Int,
	val bidPrice: Int,
	val endDate: String,
	val bidCount: Int,
	val bidStartPrice: Int,
	val isCompetitive: Boolean,
	val tradeAllowCount: Int
)
