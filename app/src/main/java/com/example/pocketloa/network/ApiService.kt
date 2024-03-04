package com.example.pocketloa.network


import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.auctionResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

	// 경매장 아이템 검색
	@POST("auction/items")
	suspend fun postMatchItems(
		@Header("authorization") token: String,
		@Body RequestBody: RequestBody

	): auctionResponse


}