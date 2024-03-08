package com.example.pocketloa.model.auction.req

import com.google.gson.annotations.SerializedName

data class RequestBody(
	@SerializedName("ItemGradeQuality") val itemGradeQuality: Int?,
	@SerializedName("ItemName") val itemName: String?,
	@SerializedName("EtcOptions") val etcOptions: List<EtcOption>?,
	@SerializedName("Sort") val sort: String?,
	@SerializedName("CategoryCode") val categoryCode: Int?,
	@SerializedName("ItemGrade") val itemGrade: String?,
	@SerializedName("PageNo") val pageNo: Int,
	@SerializedName("SortCondition") val sortCondition: String?

	)
