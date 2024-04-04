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
import com.example.pocketloa.model.auction.res.Option
import kotlinx.coroutines.launch
import okio.IOException
import java.util.EmptyStackException

class AuctionViewModel : ViewModel() {

	private val networkRepo = NetworkRepository()
	private val apiKey = BuildConfig.API_KEY

	private val auth = "bearer ${apiKey}"

	private val requestBody = RequestBody(
		itemGradeQuality = 80,
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




	fun makeReq (userInput: UserInput){

		// Todo : need mapping Option to Option code
		val etcOptions = listOf<EtcOption>(
			EtcOption(3,userInput.Engraving1.toInt(),userInput.EngravingMin1.toInt(),null),
			EtcOption(3,userInput.Engraving2.toInt(),userInput.EngravingMin2.toInt(),null),
			EtcOption(6,userInput.Penalty.toInt(),userInput.PenaltyMin.toInt(),null),

			)

		val reqBody = RequestBody(
			categoryCode = userInput.Category.toInt(),
			itemGradeQuality = userInput.Quality.toInt(),
			itemGrade = userInput.Grade,
			sortCondition = "ASC",
			etcOptions = etcOptions,
			sort = "BUY_PRICE",
			pageNo = 0
		)

		Log.d("test", "req body : ${reqBody}")


	}











}

