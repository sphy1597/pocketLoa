package com.example.pocketloa.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pocketloa.BuildConfig
import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.Item
import kotlinx.coroutines.*
import kotlin.math.ceil

class AuctionRepository {


	// LostArk API Auth token
	private val apiKey = BuildConfig.API_KEY
	private val auth = "bearer ${apiKey}"

	private val networkRepo = NetworkRepository

	private val searchResult = arrayListOf<Item>()

	private var apiCount : Int = 0
	private val oneTimeLimit : Int = 10
	private var totalCount : Int = 0
	private var leftCount : Int = 0

	suspend fun postEquipment(req: RequestBody) : List<Item>{

		try {
			totalCount = networkRepo.getHead(auth, req)

			if (totalCount <= 10){
				apiCount = totalCount
			}else{
				apiCount = oneTimeLimit
			}

			val response = networkRepo.postMatchItems(auth, req, apiCount)

			for(result in response){
				if (result.Items != null){
					searchResult.addAll(result.Items)
				}else{
					Log.d("test", "No Items postEquipment")
				}
			}

			return searchResult


		} catch (e: Error) {
			Log.e("error", "Network Error : ${e.message}")
			return searchResult
		}
	}

	suspend fun checkLimit(req: RequestBody) : Boolean{
		leftCount = networkRepo.checkLimit()
		totalCount = networkRepo.getHead(auth, req)
		return leftCount-totalCount > 0

	}




}