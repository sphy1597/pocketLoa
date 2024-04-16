package com.example.pocketloa.view.auction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocketloa.Repository.AuctionRepository
import com.example.pocketloa.model.auction.req.EtcOption
import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.Item
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AuctionRVVM() : ViewModel() {


	private val auctionRepo = AuctionRepository()

	private val itemsLiveData = MutableLiveData<List<Item>>()
	val items :  LiveData<List<Item>>
		get() = itemsLiveData

	private val stateLiveData = MutableLiveData<String>()
	val state : LiveData<String>
		get() = stateLiveData

	private var grade : String =""
	fun searchEquipment(input: UserInput) = viewModelScope.launch{
		Log.d("test", "userinput : $input")

		grade = if(input.Grade == "통합"){
			""
		}else{
			input.Grade
		}

		val etcOptions = listOf(
			EtcOption(2, convertCode(input.Stat1), convertInt(input.StatMin1), null),
			EtcOption(2, convertCode(input.Stat2), convertInt(input.StatMin2), null),
			EtcOption(3, convertCode(input.Engraving1), convertInt(input.EngravingMin1), null),
			EtcOption(3, convertCode(input.Engraving2), convertInt(input.EngravingMin2), null),
			EtcOption(6, convertCode(input.Penalty), null, convertInt(input.PenaltyMax)),
		)

		val reqBody = RequestBody(
			categoryCode = convertCode(input.Category),
			itemGrade = grade,
			itemGradeQuality = convertInt(input.Quality),
			sortCondition = "ASC",
			sort = "BUY_PRICE",
			etcOptions = etcOptions,
			pageNo = 0
		)


		if (auctionRepo.checkLimit(reqBody)){
			stateLiveData.postValue("OK")
			val result = auctionRepo.postEquipment(reqBody)
			itemsLiveData.postValue(result)
		}else{
			stateLiveData.postValue("FULL")
			this.cancel()
		}

	}




	// mapping name >> code using enum
	// convert "원한" >> 110
	private fun convertCode(input: String): Int? {
		return if (input.isNotEmpty()) {
			OptionEnum.getcodeByText(input)
		} else {
			null
		}
	}

	// if user don't select min value
	// req value must be 0 or null
	// convert "" to null or "3" >> 3
	private fun convertInt(input: String): Int? {
		return if (input.isNotEmpty()) {
			input.toInt()
		} else {
			null
		}
	}



}