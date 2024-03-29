package com.example.pocketloa.view.auction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.pocketloa.R
import com.example.pocketloa.databinding.FragmentOptionSelectBinding


class OptionSelectFragment : Fragment() {

	private lateinit var binding: FragmentOptionSelectBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

	}


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentOptionSelectBinding.inflate(inflater, container, false)
		val view = binding.root

		setupDropdown(binding.actextType, R.array.type_items)
		setupDropdown(binding.actextGrade, R.array.grade_items)
		setupDropdown(binding.actextQuality, R.array.qualities_items)
		setupDropdown(binding.actextStat1, R.array.stat_items)
		setupDropdown(binding.actextStat2, R.array.stat_items)


		binding.actextType.setOnItemClickListener { parent, view, position, id ->
			val selected = parent.getItemAtPosition(position).toString()
			if(selected == "목걸이"){
				binding.dropdownStat2.isEnabled = true
				binding.editTextMinStat2.isEnabled = true
			}else{
				binding.dropdownStat2.isEnabled = false
				binding.editTextMinStat2.isEnabled = false
			}

		}



		return view

	}

	private fun setupDropdown(actext: AutoCompleteTextView, arrayResId: Int){
		val array = resources.getStringArray(arrayResId)
		val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, array)
		actext.setAdapter(adapter)
	}


}
