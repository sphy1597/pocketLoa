package com.example.pocketloa.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coco.network.RetrofitInstance
import com.example.pocketloa.network.ApiService
import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.AuctionInfo
import com.example.pocketloa.model.auction.res.AuctionResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil


object NetworkRepository {
	private val client = RetrofitInstance()
	private val apiService : ApiService = client.getInstance().create(ApiService::class.java)

	private const val limit = 100
	private var count = 0


	private var timer : Timer? = null
	private val auctionResponse = arrayListOf<AuctionResponse>()



	fun checkLimit() : Int {
		Log.d("test", "left api count : ${limit- count}")
		return limit - count
	}


	// api토큰, request 바디를 넘겨줘야 함
	suspend fun postMatchItems(_apiToken : String, _reqBody : RequestBody, apiCount: Int) : ArrayList<AuctionResponse> {
		apiTimer(apiCount)
		auctionResponse.clear()
		try{
			for( i in 0 until apiCount){
				var pageNo = i
				_reqBody.pageNo = pageNo
				var result = apiService.postMatchItems(_apiToken, _reqBody)
				auctionResponse.add(result)
			}
		}catch (e : Error){
			Log.d("test", "network repo error postmatchItmes")
		}


		return auctionResponse
	}

	suspend fun getHead(auth : String, req: RequestBody): Int {
		val getHead = apiService.postMatchItems(auth, req)
		val total: Double = getHead.TotalCount.toDouble()
		val pageSize: Double = getHead.PageSize.toDouble()

		// 소수점 올림
		return ceil(total / pageSize).toInt()

	}


	private fun apiTimer(apiCount : Int){
		count + apiCount
		timer = Timer()
		timer?.schedule(object  : TimerTask(){
			override fun run() {
				decreaseCount(apiCount, timer)
			}
		}, 60000)

	}
	private fun decreaseCount(apiCount : Int, thisTimer : Timer?){
		count - apiCount
		thisTimer?.cancel()
	}



}