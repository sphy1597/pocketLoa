package com.example.pocketloa.view.auction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.pocketloa.R
import com.example.pocketloa.databinding.ActivityBraceletSelectBinding

class BraceletSelectActivity : AppCompatActivity() {

	private lateinit var binding: ActivityBraceletSelectBinding
	private var fixedMax = 2
	private var grantMax = 0

	private val viewModel: BraceletSelectVM by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityBraceletSelectBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.toolbarBraceletSelect.toolbarTitle.text = "팔찌 검색"

		// set bracelet grade dropdown
		val array = resources.getStringArray(R.array.bracelet_grade)
		val adapter = ArrayAdapter(this, R.layout.dropdown_item, array)
		binding.actextBraceletGrade.setAdapter(adapter)


		// 유물 >> 고정 1~2, 부여 1~2
		// 고대 >> 고정 1~2, 부여 2~3
		binding.actextBraceletGrade.setOnItemClickListener{ parent, view, position, id ->

			viewModel.setBraceletGrade(parent.getItemAtPosition(position).toString())

		}

		binding.btnFixedSubtract.setOnClickListener {
			viewModel.fixedSubtract()
		}
		binding.btnFixedPlus.setOnClickListener {
			viewModel.fixedPlus()
		}
		binding.btnGrantSubtract.setOnClickListener {
			viewModel.grantSubtract()
		}
		binding.btnGrantPlus.setOnClickListener {
			viewModel.grantPlus()
		}

		viewModel.fixed.observe(this, Observer {
			Log.d("test", "changed fixed : $it")
			binding.textFixedCount.text = it.toString()
		})

		viewModel.grant.observe(this, Observer {
			Log.d("test", "changed grant : $it")
			binding.textGrantCount.text = it.toString()
		})


	}




}