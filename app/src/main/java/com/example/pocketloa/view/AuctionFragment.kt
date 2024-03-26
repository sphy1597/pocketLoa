package com.example.pocketloa.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.pocketloa.databinding.FragmentAuctionBinding
import com.example.pocketloa.view.auction.AuctionViewModel


class AuctionFragment : Fragment() {

	private lateinit var viewModel : AuctionViewModel
	private lateinit var binding: FragmentAuctionBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val binding = FragmentAuctionBinding.inflate(inflater, container, false)
		viewModel = ViewModelProvider(this).get(AuctionViewModel::class.java)


		return binding.root
	}


}