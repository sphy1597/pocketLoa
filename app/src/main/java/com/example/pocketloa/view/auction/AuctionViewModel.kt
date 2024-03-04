package com.example.pocketloa.view.auction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocketloa.Repository.NetworkRepository
import com.example.pocketloa.model.auction.req.EtcOption
import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.auctionResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class AuctionViewModel : ViewModel() {

	private val networkRepo = NetworkRepository()

	private val token = "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDA0NTYyNDIifQ.gL-PmEdbug7rHK4L6xKqs-KDZj7VZL3Xn2HjGqMdCl9Zq3oY5j7etGBCFG6ChIrms4WYpmDElZNyyCvRN-w6dGG1-noy_6xryiQZ2NR1jmET-2BxSeb9FjZ1uX8Zn1L5bbNJVsKHJqrkKq8fC82_uY-MBI7ThDEqZi1XvqfQCpj-qUFf5VXmObVgr_-tw0dSWNc9k0jOLKxtdXDqQTnINMAtuGaFdPQu5PNpGG_CH4J5VpvBIpWN9dheF4UdEa4JcuC5aZtHQpJX_sD7nUzF3Wkw6ib1OHgxjSyuaZPzXrAYecz5eEnJGi_FkeUbHJHwDYkDoS2zj-yFmzViscmITw"

	val requestBody = RequestBody(
		itemGradeQuality = 90,
		itemName = "거룩한 예언자의 반지",
		etcOptions = listOf(
			EtcOption(firstOption = 3, secondOption = 240, minValue = 3, maxValue = null),
			EtcOption(firstOption = 3, secondOption = 255, minValue = 6, maxValue = null)
		),
		sort = "BIDSTART_PRICE",
		categoryCode = 200030,
		itemGrade = "고대",
		pageNo = 0,
		sortCondition = "ASC"
	)


	private val _itemInfo = MutableLiveData<List<auctionResponse>>()
	val iteminfo: LiveData<List<auctionResponse>>
	get() = _itemInfo

	fun postMatchItems() = viewModelScope.launch {
		val result = networkRepo.postMatchItems(token, requestBody)

		for (item in result.Items){
			Log.d("test log", "name : ${item.Name}")
		}
	}

}