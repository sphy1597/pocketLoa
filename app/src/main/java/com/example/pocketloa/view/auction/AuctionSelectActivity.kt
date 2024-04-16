package com.example.pocketloa.view.auction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.pocketloa.R
import com.example.pocketloa.databinding.ActivityAuctionSelectBinding

class AuctionSelectActivity : AppCompatActivity() {

	private lateinit var binding: ActivityAuctionSelectBinding

	private val viewModel: EquipmentDetailVM by viewModels()


	private val userInput = mutableMapOf<String, String>()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAuctionSelectBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.toolbarAuctionSelect.toolbarTitle.text = "경매장"

		initDropdown()

		// only necklace can select stat2
		// ability stone can't select stat

		binding.actextCategory.setOnItemClickListener { parent, view, position, id ->

			when (parent.getItemAtPosition(position).toString()) {
				"목걸이" -> {
					binding.dropdownStat1.isEnabled = true
					binding.editTextMinStat1.isEnabled = true
					binding.dropdownStat2.isEnabled = true
					binding.editTextMinStat2.isEnabled = true
				}
				"어빌돌" -> {
					binding.dropdownStat1.isEnabled = false
					binding.editTextMinStat1.isEnabled = false
					binding.dropdownStat2.isEnabled = false
					binding.editTextMinStat2.isEnabled = false
					binding.actextStat1.setText("")
					binding.editTextMinStat1.setText("")
					binding.actextStat2.setText("")
					binding.editTextMinStat2.setText("")
				}
				else -> {
					binding.dropdownStat1.isEnabled = true
					binding.editTextMinStat1.isEnabled = true
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
				Toast.makeText(this, "부위를 선택해주세요", Toast.LENGTH_SHORT).show()
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
					PenaltyMax = binding.editTextMaxPenalty.text.toString()
				)


				val intent = Intent(this, AuctionRVActivity::class.java)

				// 데이터 넣기
				intent.apply {
					intent.putExtra("Category", binding.actextCategory.text.toString())
					intent.putExtra("Grade", binding.actextGrade.text.toString())
					intent.putExtra("Quality", binding.actextQuality.text.toString())
					intent.putExtra("Stat1", binding.actextStat1.text.toString())
					intent.putExtra("StatMin1", binding.editTextMinStat1.text.toString())
					intent.putExtra("Stat2", binding.actextStat2.text.toString())
					intent.putExtra("StatMin2", binding.editTextMinStat2.text.toString())
					intent.putExtra("Engraving1", binding.actextEngraving1.text.toString())
					intent.putExtra("EngravingMin1", binding.editTextMinEngraving1.text.toString())
					intent.putExtra("Engraving2", binding.actextEngraving2.text.toString())
					intent.putExtra("EngravingMin2", binding.editTextMinEngraving2.text.toString())
					intent.putExtra("Penalty", binding.actextPenalty.text.toString())
					intent.putExtra("PenaltyMax", binding.editTextMaxPenalty.text.toString())
				}
				startActivity(intent)

			}
		}

	}

	//	 add drop down list
//	 get array res >> values >> array.xml
	private fun setupDropdown(actext: AutoCompleteTextView, arrayResId: Int) {
		val array = resources.getStringArray(arrayResId)
		val adapter = ArrayAdapter(this, R.layout.dropdown_item, array)
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