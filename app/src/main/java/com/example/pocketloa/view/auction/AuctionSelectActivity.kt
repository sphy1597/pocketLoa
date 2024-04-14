package com.example.pocketloa.view.auction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pocketloa.R
import com.example.pocketloa.databinding.ActivityAuctionSelectBinding

class AuctionSelectActivity : AppCompatActivity() {
	private lateinit var binding : ActivityAuctionSelectBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAuctionSelectBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.toolbarAuctionSelect.toolbarTitle.text = "경매장"


	}
}