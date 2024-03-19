package com.example.pocketloa.model.auction

import androidx.core.location.LocationRequestCompat.Quality

data class ItemInfo(
	val itemName : String,
	val grade : String,
	val gradeQuality: String,
	val buyPrice : Int,
	val statOption : String,
	val firstOption : String,
	val secondOption: String

)
