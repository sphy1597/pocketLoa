package com.example.pocketloa.model.auction.res

data class Option(
    val ClassName: String,
    val IsPenalty: Boolean,
    val OptionName: String,
    val OptionNameTripod: String,
    val Type: String, // 스탯, 어빌리티
    val Value: Int
)