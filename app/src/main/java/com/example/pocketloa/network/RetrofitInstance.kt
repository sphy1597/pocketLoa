package com.example.coco.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitInstance {

	private val BASE_URL = "https://developer-lostark.game.onstove.com/"

	private val okHttpClient : OkHttpClient by lazy {
		OkHttpClient.Builder( )
			.addInterceptor(HttpLoggingInterceptor().apply {
				level = HttpLoggingInterceptor.Level.BODY
			})
			.build()
	}


	private val client = Retrofit
		.Builder()
		.baseUrl(BASE_URL)
		.client(okHttpClient)
		.addConverterFactory(MoshiConverterFactory.create()) // gson객체에 Lenient속성 설정
		.build()

	fun getInstance() : Retrofit{
		return  client
	}


}