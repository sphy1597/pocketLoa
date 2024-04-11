package com.example.pocketloa.Repository

import com.example.coco.network.RetrofitInstance
import com.example.pocketloa.network.ApiService
import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.AuctionInfo
import com.example.pocketloa.model.auction.res.AuctionResponse


class NetworkRepository {
	private val apiService : ApiService = RetrofitInstance.getInstance().create(ApiService::class.java)

	private var apiLimit = 0

	// api토큰, request 바디를 넘겨줘야 함
	suspend fun postMatchItems(_apiToken : String, _reqBody : RequestBody) : AuctionResponse {
		apiLimit++
		return apiService.postMatchItems(_apiToken, _reqBody)
	}



}