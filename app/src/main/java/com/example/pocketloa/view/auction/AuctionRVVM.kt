package com.example.pocketloa.view.auction

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pocketloa.Repository.AuctionRepository
import com.example.pocketloa.model.auction.res.Item

class AuctionRVVM() : ViewModel() {

	private val auctionRepo = AuctionRepository


	fun getResult() : List<Item>{
		Log.d("test", "getResult")

		return auctionRepo.getList()
	}





}