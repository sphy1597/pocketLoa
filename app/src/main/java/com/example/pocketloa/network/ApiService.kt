package com.example.pocketloa.network


import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.AuctionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

	// 경매장 아이템 검색

	@POST("auctions/items")
	suspend fun postMatchItems(
		@Header("authorization") token: String,
		@Body RequestBody: RequestBody

	): AuctionResponse


}