package com.example.pocketloa.view.auction

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.pocketloa.R
import com.example.pocketloa.databinding.FragmentAccesoriesSelectBinding
import com.example.pocketloa.databinding.FragmentAuctionBinding


class AuctionFragment : Fragment() {

	private lateinit var viewModel : AuctionViewModel
	private var _binding: FragmentAuctionBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentAuctionBinding.inflate(inflater, container, false)
		return binding.root
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnAccessories.setOnClickListener {
			replaceFragment(OptionSelectFragment())
		}


	}

	private fun replaceFragment(fragment : Fragment){
		val transaction = childFragmentManager.beginTransaction()
		transaction.replace(R.id.frame_auction, fragment)
		transaction.addToBackStack(null)
		transaction.commit()
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}



}