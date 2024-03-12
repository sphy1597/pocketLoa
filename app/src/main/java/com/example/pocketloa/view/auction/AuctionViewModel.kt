package com.example.pocketloa.view.auction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocketloa.Repository.NetworkRepository
import com.example.pocketloa.model.auction.ItemInfo
import com.example.pocketloa.model.auction.req.EtcOption
import com.example.pocketloa.model.auction.req.RequestBody
import com.example.pocketloa.model.auction.res.AuctionResponse
import com.example.pocketloa.model.auction.res.Item
import com.google.gson.Gson
import kotlinx.coroutines.launch

class AuctionViewModel : ViewModel() {

	private val networkRepo = NetworkRepository()

	private val token = "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDA0NTYyNDIifQ.gL-PmEdbug7rHK4L6xKqs-KDZj7VZL3Xn2HjGqMdCl9Zq3oY5j7etGBCFG6ChIrms4WYpmDElZNyyCvRN-w6dGG1-noy_6xryiQZ2NR1jmET-2BxSeb9FjZ1uX8Zn1L5bbNJVsKHJqrkKq8fC82_uY-MBI7ThDEqZi1XvqfQCpj-qUFf5VXmObVgr_-tw0dSWNc9k0jOLKxtdXDqQTnINMAtuGaFdPQu5PNpGG_CH4J5VpvBIpWN9dheF4UdEa4JcuC5aZtHQpJX_sD7nUzF3Wkw6ib1OHgxjSyuaZPzXrAYecz5eEnJGi_FkeUbHJHwDYkDoS2zj-yFmzViscmITw"

	val requestBody = RequestBody(
		itemGradeQuality = 80,
		itemName = "참혹한 파멸의 목걸이",
		etcOptions = listOf(
			EtcOption(firstOption = 3, secondOption = 195, minValue = 1, maxValue = null),
			EtcOption(firstOption = 3, secondOption = 301, minValue = 1, maxValue = null)
		),
		sort = "BIDSTART_PRICE",
		categoryCode = 200010,
		itemGrade = "고대",
		pageNo = 1,
		sortCondition = "ASC"
	)

	private val _LiveItems = MutableLiveData<List<Item>>()
	val LiveItems: LiveData<List<Item>>
		get() = _LiveItems
	fun postMatchItems() = viewModelScope.launch {
		val result = networkRepo.postMatchItems(token, requestBody)
		result.Items?.let{items ->
				_LiveItems.postValue(items)

			}


		}
	}

