package com.example.pocketloa.network.model.auction

data class Option(
	val type: String,
	val optionName: String,
	val optionNameTripod: String,
	val value: Int,
	val isPenalty: Boolean,
	val className: String?
)