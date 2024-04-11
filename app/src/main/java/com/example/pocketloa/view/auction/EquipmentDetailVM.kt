package com.example.pocketloa.view.auction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pocketloa.Repository.AuctionRepository
import com.example.pocketloa.model.auction.req.EtcOption
import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.Item

class EquipmentDetailVM() : ViewModel() {


	private val auctionRepo = AuctionRepository




	// make req body
	fun makeReq(userInput: UserInput) {

		// Todo : other options have total too
		val grade: String
		if (userInput.Grade == "통합") {
			grade = ""
		} else {
			grade = userInput.Grade
		}

		val etcOptions = listOf(
			EtcOption(2, convertCode(userInput.Stat1), convertInt(userInput.StatMin1), null),
			EtcOption(2, convertCode(userInput.Stat2), convertInt(userInput.StatMin2), null),
			EtcOption(
				3,
				convertCode(userInput.Engraving1),
				convertInt(userInput.EngravingMin1),
				null
			),
			EtcOption(
				3,
				convertCode(userInput.Engraving2),
				convertInt(userInput.EngravingMin2),
				null
			),
			EtcOption(6, convertCode(userInput.Penalty), convertInt(userInput.PenaltyMin), null),

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

		auctionRepo.auctionManager(reqBody)

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

