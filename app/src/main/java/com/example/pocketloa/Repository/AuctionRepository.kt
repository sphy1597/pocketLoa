package com.example.pocketloa.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pocketloa.BuildConfig
import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.Item
import kotlinx.coroutines.*
import kotlin.math.ceil

object AuctionRepository {


	private val searchResult = arrayListOf<Item>()

	private val _apiState = MutableLiveData<String>()
	val apiState: LiveData<String>
		get() = _apiState

	fun getdata(): ArrayList<Item> {
		return searchResult
	}

	// LostArk API Auth token
	private val apiKey = BuildConfig.API_KEY
	private val auth = "bearer ${apiKey}"

	// coroutine
	private var job: Job? = null

	private val networkRepo = NetworkRepository

	fun getList(): ArrayList<Item> {
		Log.d("test", "getList")
		_apiState.postValue("wait")
		return searchResult
	}


	fun auctionManager(req: RequestBody) {
		if (job?.isActive == true) {
			return
		}
		job = CoroutineScope(Dispatchers.IO).launch {
			val head = getHead(req)
			for ( i in 0 until head){
				postEquipment(req, i)
			}

		}
	}


	private suspend fun postEquipment(req: RequestBody, pageNo : Int) {

		try {
			req.pageNo = pageNo
			_apiState.postValue("searching")
			val result = networkRepo.postMatchItems(auth, req)
			if (result.Items != null) {
				searchResult.addAll(result.Items)
				Log.d("test", "add all result.item")
				_apiState.postValue("finish")

			} else {
				Log.e("error", "No Items, 검색된 매물이 없습니다.")
			}



		} catch (e: Error) {
			Log.e("error", "Network Error : ${e.message}")
		}
	}

	private suspend fun getHead(req: RequestBody): Int {
		val getHead = networkRepo.postMatchItems(auth, req)
		val total: Double = getHead.TotalCount.toDouble()
		val pageSize: Double = getHead.PageSize.toDouble()

		// 소수점 올림
		return ceil(total / pageSize).toInt()

	}


}