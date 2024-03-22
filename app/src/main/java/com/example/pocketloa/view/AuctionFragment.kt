package com.example.pocketloa.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.pocketloa.R
import com.example.pocketloa.view.auction.AuctionRVActivity
import com.example.pocketloa.view.auction.AuctionSelectActivity
import com.example.pocketloa.view.auction.AuctionViewModel


class AuctionFragment : Fragment() {

	private lateinit var viewModel : AuctionViewModel


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_auction, container, false)
		viewModel = ViewModelProvider(this).get(AuctionViewModel::class.java)

		view.findViewById<Button>(R.id.auction_fragment_btn).setOnClickListener {
			val intent = Intent(requireContext(), AuctionSelectActivity::class.java)
			startActivity(intent)
		}
		return view
	}


}