package com.example.pocketloa.model.auction.req

import com.google.gson.annotations.SerializedName

data class EtcOption(
	@SerializedName("FirstOption") val firstOption: Int,
	@SerializedName("SecondOption") val secondOption: Int,
	@SerializedName("MinValue") val minValue: Int,
	@SerializedName("MaxValue") val maxValue: Int?
)
