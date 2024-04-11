package com.example.pocketloa.view.auction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocketloa.Repository.AuctionRepository
import com.example.pocketloa.databinding.RvAuctionResultBinding
import com.example.pocketloa.view.adapter.AuctionRVAdapter

class AuctionRVActivity : AppCompatActivity() {

	private lateinit var binding : RvAuctionResultBinding

	private val viewModel : AuctionRVVM by viewModels()

	private lateinit var auctionAdapter: AuctionRVAdapter




	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = RvAuctionResultBinding.inflate(layoutInflater)
		setContentView(binding.root)

		auctionAdapter = AuctionRVAdapter(this, viewModel.getResult() )
		binding.rvAuctionResult.adapter = auctionAdapter
		binding.rvAuctionResult.layoutManager = LinearLayoutManager(this )


		Log.d("test", "onCreate rv")

		AuctionRepository.apiState.observe(this, Observer {
			Log.d("test", "state : $it")
			when(it){
				"finish" -> {
					Log.d("test", "finish")
					viewModel.getResult()
				}
				"start" -> {
					Log.d("test", "start")
					Toast.makeText(this, "검색 중 입니다.", Toast.LENGTH_SHORT).show()
				}
				"wait" -> {
					Log.d("test", "wait")
					Toast.makeText(this, "다시 검색해주세요", Toast.LENGTH_SHORT).show()
				}

			}
		})


	}
}