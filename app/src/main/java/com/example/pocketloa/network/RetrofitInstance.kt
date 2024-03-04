package com.example.coco.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

	// Json포멧 데이터를 GSON 라이브러리를 통해 사용할 때 발생할 수 있는 오류 대처
	private val gson = GsonBuilder()
		.setLenient()
		.create()

	private const val BASE_URL = "https://developer-lostark.game.onstove.com/"
	private val client = Retrofit
		.Builder()
		.baseUrl(BASE_URL)
		.addConverterFactory(GsonConverterFactory.create(gson)) // gson객체에 Lenient속성 설정
		.build()

	fun getInstance() : Retrofit{
		return  client
	}


}