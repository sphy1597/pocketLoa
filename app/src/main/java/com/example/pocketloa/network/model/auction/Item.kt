package com.example.pocketloa.network.model.auction

data class Item(
	val name: String,
	val grade: String,
	val tier: Int,
	val level: Int?,
	val icon: String,
	val gradeQuality: Int,
	val auctionInfo: AuctionInfo,
	val options: List<Option>
)
