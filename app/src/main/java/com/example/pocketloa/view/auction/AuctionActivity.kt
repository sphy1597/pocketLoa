package com.example.pocketloa.view.auction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.pocketloa.R
import com.example.pocketloa.databinding.ActivityAuctionBinding
import com.example.pocketloa.view.adapter.AuctionAdapter

class AuctionActivity : AppCompatActivity() {

	private lateinit var binding : ActivityAuctionBinding

	private val viewModel : AuctionViewModel by viewModels()

	private lateinit var auctionAdapter: AuctionAdapter


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAuctionBinding.inflate(layoutInflater)
		setContentView(binding.root)

		viewModel.postMatchItems()


	}
}