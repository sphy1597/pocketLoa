package com.example.pocketloa.view.auction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocketloa.databinding.RvAuctionResultBinding
import com.example.pocketloa.view.adapter.AuctionRVAdapter

class AuctionRVActivity : AppCompatActivity() {

	private lateinit var binding: RvAuctionResultBinding

	private val viewModel: AuctionRVVM by viewModels()

	private lateinit var auctionAdapter: AuctionRVAdapter


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = RvAuctionResultBinding.inflate(layoutInflater)
		setContentView(binding.root)

		// category는 null 이면 이전 Activity에서 넘어오지 않음
		val userInput = UserInput(
			Category = intent.getStringExtra("Category") ?: "",
			Grade = intent.getStringExtra("Grade") ?: "",
			Quality = intent.getStringExtra("Quality") ?: "",
			Stat1 = intent.getStringExtra("Stat1") ?: "",
			StatMin1 = intent.getStringExtra("StatMin1") ?: "",
			Stat2 = intent.getStringExtra("Stat2") ?: "",
			StatMin2 = intent.getStringExtra("StatMin2") ?: "",
			Engraving1 = intent.getStringExtra("Engraving1") ?: "",
			EngravingMin1 = intent.getStringExtra("EngravingMin1") ?: "",
			Engraving2 = intent.getStringExtra("Engraving2") ?: "",
			EngravingMin2 = intent.getStringExtra("EngravingMin2") ?: "",
			Penalty = intent.getStringExtra("Penalty") ?: "",
			PenaltyMax = intent.getStringExtra("PenaltyMax") ?: ""
		)

		viewModel.searchEquipment(userInput)

		viewModel.items.observe(this, Observer {
			auctionAdapter = AuctionRVAdapter(this, it)
			binding.rvAuctionResult.adapter = auctionAdapter
			binding.rvAuctionResult.layoutManager = LinearLayoutManager(this )
		})

		viewModel.state.observe(this, Observer {
			Toast.makeText(this, "API State : $it", Toast.LENGTH_SHORT).show()
		})



	}
}