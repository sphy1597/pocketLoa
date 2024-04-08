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


	// LostArk API Auth token
	// todo : get by user input
	private val auth = "bearer ${apiKey}"



	// Search result live data
	private val _liveItems = MutableLiveData<List<Item>>()
	val liveItems: LiveData<List<Item>>
		get() = _liveItems




	// function of Euqipment Detail Fragment search button
	fun auctionSearch (userInput: UserInput){

		val req = makeReq(userInput)


	}



	// make req body
	fun makeReq (userInput: UserInput) : RequestBody{

		// Todo : other options have total too
		val grade : String
		if(userInput.Grade == "통합"){
			grade = ""
		}else{
			grade = userInput.Grade
		}

		val etcOptions = listOf(
			EtcOption(2,convertCode(userInput.Stat1), convertInt(userInput.StatMin1), null),
			EtcOption(2,convertCode(userInput.Stat2), convertInt(userInput.StatMin2), null),
			EtcOption(3,convertCode(userInput.Engraving1), convertInt(userInput.EngravingMin1),null),
			EtcOption(3,convertCode(userInput.Engraving2), convertInt(userInput.EngravingMin2),null),
			EtcOption(6,convertCode(userInput.Penalty), convertInt(userInput.PenaltyMin),null),

			)

		val reqBody = RequestBody(
			categoryCode = convertCode(userInput.Category),
			itemGradeQuality = convertInt(userInput.Quality),
			itemGrade = grade,
			sortCondition = "ASC",
			etcOptions = etcOptions,
			sort = "BUY_PRICE",
			pageNo = 0
		)

		return reqBody

	}

	// mapping name >> code using enum
	// convert "원한" >> 110
	private fun convertCode (input : String) : Int?{
		return if(input.isNotEmpty()){
			OptionEnum.getcodeByText(input)
		}else{
			null
		}
	}

	// if user don't select min value
	// req value must be 0 or null
	// convert "" to null or "3" >> 3
	private fun convertInt ( input : String ): Int?{
		return if(input.isNotEmpty()){
			input.toInt()
		}else{
			null
		}
	}


	//	fun postMatchItems() = viewModelScope.launch {
//		Log.d("test log", "Run postMatchItems of Auction View model")
//		val result = networkRepo.postMatchItems(auth, requestBody)
//
//		try{
//
//			if(result.Items != null){
//				_liveItems.postValue(result.Items)
//			}else{
//				Log.e("NoItemError", "검색된 매물이 없습니다.")
//			}
//
//		}catch (networkException : IOException){
//			Log.e("NetworkError", "인터넷 연결 에러")
//
//		}
//
//	}

}

