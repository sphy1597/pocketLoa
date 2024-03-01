package com.example.pocketloa.network


import com.example.pocketloa.network.model.auction.ApiResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {

	// 경매장 아이템 검색
	@POST("auction/items")
	suspend fun postMatchItems(@Header("authorization") token : String) : Call<ApiResponse>
}