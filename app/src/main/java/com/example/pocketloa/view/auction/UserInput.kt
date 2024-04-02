package com.example.pocketloa.view.auction

data class UserInput(
	val Type : String,
	val Grade : String?,
	val Quality : String?,
	val Stat1 : String?,
	val StatMin1 : Int?,
	val Stat2 : String?,
	val StatMin2 : Int?,
	val Engraving1 : String?,
	val Emin1 : Int?,
	val Engraving2: String?,
	val Emin2 : Int?,
	val Penalty : String?,
	val PenaltyMin : Int?
)
