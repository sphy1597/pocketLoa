package com.example.pocketloa.view.auction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocketloa.databinding.ActivityAuctionBinding
import com.example.pocketloa.view.adapter.AuctionRVAdapter

class AuctionActivity : AppCompatActivity() {

	private lateinit var binding : ActivityAuctionBinding

	private val viewModel : AuctionViewModel by viewModels()

	private lateinit var auctionAdapter: AuctionRVAdapter


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAuctionBinding.inflate(layoutInflater)
		setContentView(binding.root)

		viewModel.postMatchItems()
		viewModel.LiveItems.observe(this, Observer {
			auctionAdapter = AuctionRVAdapter(this, it)
			binding.auctionRV.adapter = auctionAdapter
			binding.auctionRV.layoutManager = LinearLayoutManager(this)


		})


	}
}