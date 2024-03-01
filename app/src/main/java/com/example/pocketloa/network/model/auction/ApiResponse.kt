package com.example.pocketloa.network.model.auction

data class ApiResponse(
	val pageNo: Int,
	val pageSize: Int,
	val totalCount: Int,
	val items: List<Item>
)
