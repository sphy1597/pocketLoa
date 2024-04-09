package com.example.pocketloa.view.auction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocketloa.databinding.RvAuctionResultBinding
import com.example.pocketloa.view.adapter.AuctionRVAdapter

class AuctionRVActivity : AppCompatActivity() {

	private lateinit var binding : RvAuctionResultBinding

	private val viewModel : AuctionViewModel by viewModels()

	private lateinit var auctionAdapter: AuctionRVAdapter



	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = RvAuctionResultBinding.inflate(layoutInflater)
		setContentView(binding.root)

		Log.d("test", "RV on Create")
		viewModel.updateLiveData()

		viewModel.liveItems.observe(this, Observer {
			Log.d("test live data", "${it.size}")

			auctionAdapter = AuctionRVAdapter(this, it)
			binding.rvAuctionResult.adapter = auctionAdapter
			binding.rvAuctionResult.layoutManager = LinearLayoutManager(this)


		})





	}


}