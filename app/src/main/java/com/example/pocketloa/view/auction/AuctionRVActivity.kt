package com.example.pocketloa.view.auction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocketloa.databinding.ActivityAuctionRvBinding
import com.example.pocketloa.view.adapter.AuctionRVAdapter

class AuctionRVActivity : AppCompatActivity() {

	private lateinit var binding : ActivityAuctionRvBinding

	private val viewModel : AuctionViewModel by viewModels()


	private lateinit var auctionAdapter: AuctionRVAdapter


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAuctionRvBinding.inflate(layoutInflater)
		setContentView(binding.root)


		Log.d("test log", "onCreate Auction RV Activity")

		viewModel.postMatchItems()

		Log.d("test log", "Start viewModel.postMatchItems()")

		viewModel.liveItems.observe(this, Observer {
			auctionAdapter = AuctionRVAdapter(this, it)
			binding.auctionRV.adapter = auctionAdapter
			binding.auctionRV.layoutManager = LinearLayoutManager(this)


		})


	}
}