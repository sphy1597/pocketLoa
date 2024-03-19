package com.example.pocketloa.Repository

import com.example.coco.network.RetrofitInstance
import com.example.pocketloa.network.ApiService
import com.example.pocketloa.model.auction.req.RequestBody



class NetworkRepository {
	private val apiService : ApiService = RetrofitInstance.getInstance().create(ApiService::class.java)

	// api토큰, request 바디를 넘겨줘야 함
	suspend fun postMatchItems(_apiToken : String, _reqBody : RequestBody) = apiService.postMatchItems(_apiToken, _reqBody)



}