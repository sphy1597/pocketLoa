package com.example.pocketloa.view.auction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pocketloa.R
import com.example.pocketloa.databinding.FragmentEquipmentDetailBinding


class EquipmentDetailFragment : Fragment() {

	private lateinit var binding: FragmentEquipmentDetailBinding
	private lateinit var viewmodel : AuctionViewModel

	private val userInput = mutableMapOf<String, String?>()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewmodel = ViewModelProvider(this).get(AuctionViewModel::class.java)

	}



	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentEquipmentDetailBinding.inflate(inflater, container, false)
		val view = binding.root

		// dropdown 설정
		initDropdown()

		return view

	}

	// View 초기값 설정, LiveData옵저빙, Adapter(Rv, viewpager2) 세팅
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)





		// 장비 마다 옵션 선택 가능 여부 변경
		binding.actextType.setOnItemClickListener { parent, view, position, id ->

			when(parent.getItemAtPosition(position).toString()){
				"목걸이" -> {
					binding.dropdownStat2.isEnabled = true
					binding.editTextMinStat2.isEnabled = true
				}
				"어빌돌" -> {
					binding.dropdownStat1.isEnabled = false
					binding.editTextMinStat1.isEnabled = false
					binding.dropdownStat2.isEnabled = false
					binding.editTextMinStat2.isEnabled = false
				}
				else -> {
					binding.dropdownStat2.isEnabled = false
					binding.editTextMinStat2.isEnabled = false
					binding.actextStat2.setText("")
					binding.editTextMinStat2.setText("")
				}
			}
		}

		// 선택 안됐으면 .equals("")
		binding.btnOptionSearch.setOnClickListener{

			userInput["Type"]=binding.actextType.text.toString()
			userInput["Grade"] = binding.actextGrade.text.toString()
			userInput["Quality"] = binding.actextQuality.text.toString()
			userInput["Stat1"] = binding.actextStat1.text.toString()
			userInput["StatMin1"] = binding.editTextMinStat1.text.toString()
			userInput["Stat2"] = binding.actextStat2.text.toString()
			userInput["StatMin2"] = binding.editTextMinStat2.text.toString()
			userInput["Engraving1"] = binding.actextEngraving1.text.toString()
			userInput["EngravingMin1"] = binding.editTextMinEngraving1.text.toString()
			userInput["Engraving2"] = binding.actextEngraving2.text.toString()
			userInput["EngravingMin2"] = binding.editTextMinEngraving2.text.toString()
			userInput["Penalty"] = binding.actextPenalty.text.toString()
			userInput["PenaltyMin"] = binding.editTextMinPenalty.text.toString()

			viewmodel.userInputTest(userInput)
		}

	}

	private fun setupDropdown(actext: AutoCompleteTextView, arrayResId: Int){
		val array = resources.getStringArray(arrayResId)
		val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, array)
		actext.setAdapter(adapter)
	}

	private fun initDropdown(){
		setupDropdown(binding.actextType, R.array.type_items)
		setupDropdown(binding.actextGrade, R.array.grade_items)
		setupDropdown(binding.actextQuality, R.array.qualities_items)
		setupDropdown(binding.actextStat1, R.array.stat_items)
		setupDropdown(binding.actextStat2, R.array.stat_items)
		setupDropdown(binding.actextEngraving1, R.array.engraving_items)
		setupDropdown(binding.actextEngraving2, R.array.engraving_items)
		setupDropdown(binding.actextPenalty, R.array.penalty_items)
	}



}
