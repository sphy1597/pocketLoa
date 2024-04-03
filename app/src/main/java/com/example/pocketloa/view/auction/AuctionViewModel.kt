package com.example.pocketloa.view.auction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocketloa.BuildConfig
import com.example.pocketloa.Repository.NetworkRepository
import com.example.pocketloa.model.auction.req.EtcOption
import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.Item
import kotlinx.coroutines.launch
import okio.IOException

class AuctionViewModel : ViewModel() {

	private val networkRepo = NetworkRepository()
	private val apiKey = BuildConfig.API_KEY

	private val auth = "bearer ${apiKey}"

	private val requestBody = RequestBody(
		itemGradeQuality = 80,
		itemName = "참혹한 파멸의 목걸이",
		etcOptions = listOf(
			EtcOption(firstOption = 3, secondOption = 195, minValue = 1, maxValue = null),
			EtcOption(firstOption = 3, secondOption = 301, minValue = 1, maxValue = null)
		),
		sort = "BIDSTART_PRICE",
		categoryCode = 200010,
		itemGrade = "고대",
		pageNo = 1,
		sortCondition = "ASC"
	)

	private val _liveItems = MutableLiveData<List<Item>>()
	val liveItems: LiveData<List<Item>>
		get() = _liveItems

	fun postMatchItems() = viewModelScope.launch {
		Log.d("test log", "Run postMatchItems of Auction View model")
		val result = networkRepo.postMatchItems(auth, requestBody)

		try{

			if(result.Items != null){
				_liveItems.postValue(result.Items)
			}else{
				Log.e("NoItemError", "검색된 매물이 없습니다.")
			}

		}catch (networkException : IOException){
			Log.e("NetworkError", "인터넷 연결 에러")

		}

	}



	fun userInputTest(userInput : MutableMap<String, String?>){
		for((key,value) in userInput){
			Log.d("test", "Key : ${key}, value : ${value}")
		}
	}










}

