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

		initListener()


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
				}
			}
		}

		// 선택 안됐으면 .equals("")
		binding.btnOptionSearch.setOnClickListener{
			viewmodel.userInputTest()
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

	private fun initListener(){
		binding.actextType.addTextChangedListener{
			viewmodel.setUserInput("Type", binding.actextType.text.toString())
		}

		binding.actextGrade.addTextChangedListener {
			viewmodel.setUserInput("Grade", binding.actextGrade.text.toString())
		}
		binding.actextQuality.addTextChangedListener {
			viewmodel.setUserInput("Quality", binding.actextQuality.text.toString())
		}
		binding.actextStat1.addTextChangedListener {
			viewmodel.setUserInput("Stat1", binding.actextStat1.text.toString())
		}
		binding.editTextMinStat1.addTextChangedListener{
			viewmodel.setUserInput("StatMin1", binding.editTextMinStat1.text.toString())
		}
		binding.actextStat2.addTextChangedListener {
			viewmodel.setUserInput("Stat2", binding.actextStat2.text.toString())
		}
		binding.editTextMinStat2.addTextChangedListener{
			viewmodel.setUserInput("StatMin2", binding.editTextMinStat2.text.toString())
		}

		binding.actextEngraving1.addTextChangedListener {
			viewmodel.setUserInput("Engraving1", binding.actextEngraving1.text.toString())
		}
		binding.editTextMinEngraving1.addTextChangedListener {
			viewmodel.setUserInput("EngravingMin1", binding.editTextMinEngraving1.text.toString())
		}

		binding.actextEngraving2.addTextChangedListener {
			viewmodel.setUserInput("Engraving2", binding.actextEngraving2.text.toString())
		}
		binding.editTextMinEngraving2.addTextChangedListener {
			viewmodel.setUserInput("EngravingMin2", binding.editTextMinEngraving2.text.toString())
		}

		binding.actextPenalty.addTextChangedListener {
			viewmodel.setUserInput("Penalty", binding.actextPenalty.text.toString())
		}
		binding.editTextMinPenalty.addTextChangedListener {
			viewmodel.setUserInput("PenaltyMin", binding.editTextMinPenalty.text.toString())
		}

	}



}
