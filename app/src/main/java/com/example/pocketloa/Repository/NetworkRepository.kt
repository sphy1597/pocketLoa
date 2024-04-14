package com.example.pocketloa.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coco.network.RetrofitInstance
import com.example.pocketloa.network.ApiService
import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.AuctionInfo
import com.example.pocketloa.model.auction.res.AuctionResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


object NetworkRepository {
	private val client = RetrofitInstance()
	private val apiService : ApiService = client.getInstance().create(ApiService::class.java)

	private val limit = 100
	private var count = 0

	private val _apiState = MutableLiveData<String>()
	val apiState: LiveData<String>
		get() = _apiState



	// api토큰, request 바디를 넘겨줘야 함
	suspend fun postMatchItems(_apiToken : String, _reqBody : RequestBody) : AuctionResponse {
		count++
		return apiService.postMatchItems(_apiToken, _reqBody)
	}

	fun checkLimit() : Int{
		return limit - count

	}



}