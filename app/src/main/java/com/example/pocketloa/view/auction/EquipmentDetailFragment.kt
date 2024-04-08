package com.example.pocketloa.view.auction

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.pocketloa.R
import com.example.pocketloa.databinding.FragmentEquipmentDetailBinding


class EquipmentDetailFragment : Fragment() {

	private lateinit var binding: FragmentEquipmentDetailBinding
	private lateinit var viewmodel: AuctionViewModel

	private val userInput = mutableMapOf<String, String>()
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

		// dropdown setting
		initDropdown()

		return view

	}

	// View init setting, LiveData Observing, Adapter(Rv, viewpager2) setting
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)


		binding.actextQuality.addTextChangedListener {
			Log.d("test", "on Changed actextQuality")
		}


		// only necklace can select stat2
		// ability stone can't select stat
		binding.actextCategory.setOnItemClickListener { parent, view, position, id ->

			when (parent.getItemAtPosition(position).toString()) {
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

		// isEmpty >> ""
		binding.btnOptionSearch.setOnClickListener {

			if (binding.actextCategory.text.isEmpty()) {
				Log.d("test", "No Select equipment")
				Toast.makeText(requireContext(), "부위를 선택해주세요", Toast.LENGTH_SHORT).show()
			} else {

				val userInput = UserInput(
					// can't convert Int beacuse isEmpty value is ""
					Category = binding.actextCategory.text.toString(),
					Grade = binding.actextGrade.text.toString(),
					Quality = binding.actextQuality.text.toString(),
					Stat1 = binding.actextStat1.text.toString(),
					StatMin1 = binding.editTextMinStat1.text.toString(),
					Stat2 = binding.actextStat2.text.toString(),
					StatMin2 = binding.editTextMinStat2.text.toString(),
					Engraving1 = binding.actextEngraving1.text.toString(),
					EngravingMin1 = binding.editTextMinEngraving1.text.toString(),
					Engraving2 = binding.actextEngraving2.text.toString(),
					EngravingMin2 = binding.editTextMinEngraving2.text.toString(),
					Penalty = binding.actextPenalty.text.toString(),
					PenaltyMin = binding.editTextMinPenalty.text.toString()
				)

				viewmodel.auctionSearch(userInput)

			}
		}

	}

	// add drop down list
	// get array res >> values >> array.xml
	private fun setupDropdown(actext: AutoCompleteTextView, arrayResId: Int) {
		val array = resources.getStringArray(arrayResId)
		val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, array)
		actext.setAdapter(adapter)
	}

	private fun initDropdown() {
		setupDropdown(binding.actextCategory, R.array.type_items)
		setupDropdown(binding.actextGrade, R.array.grade_items)
		setupDropdown(binding.actextQuality, R.array.qualities_items)
		setupDropdown(binding.actextStat1, R.array.stat_items)
		setupDropdown(binding.actextStat2, R.array.stat_items)
		setupDropdown(binding.actextEngraving1, R.array.engraving_items)
		setupDropdown(binding.actextEngraving2, R.array.engraving_items)
		setupDropdown(binding.actextPenalty, R.array.penalty_items)
	}


}
