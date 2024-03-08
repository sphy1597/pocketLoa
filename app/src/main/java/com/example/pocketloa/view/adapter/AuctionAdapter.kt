package com.example.pocketloa.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.location.LocationRequestCompat.Quality
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketloa.R
import com.example.pocketloa.model.auction.res.Item


class AuctionAdapter(val context: Context, private val itemList: List<Item>) : RecyclerView.Adapter<AuctionAdapter.ViewHolder>(){

	inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
		private val itemName : TextView = itemView.findViewById(R.id.item_name)
		private val grade : TextView = itemView.findViewById(R.id.grade)
		private val gradeQuality : TextView = itemView.findViewById(R.id.grade_quality)
		private val buyPrice : TextView = itemView.findViewById(R.id.buy_price)
		private val statOption : TextView = itemView.findViewById(R.id.stat_option)
		private val firstOption : TextView = itemView.findViewById(R.id.first_ability)
		private val secondOption : TextView = itemView.findViewById(R.id.second_ability)

		fun bind(item: Item){
			var stat = item.Options[0].OptionName + item.Options[0].Value
			var first = item.Options[1].OptionName + item.Options[1].Value
			var second = item.Options[2].OptionName + item.Options[2].Value
			if(item.Grade == "고대"){
				itemName.setTextColor(Color.parseColor("#C9A472"))
			}else if (item.Grade == "유물"){
				itemName.setTextColor(Color.parseColor("#FF6000"))
			}

			itemName.text = item.Name
			grade.text = item.Grade
			gradeQuality.text = item.GradeQuality.toString()
			buyPrice.text = item.AuctionInfo.BuyPrice.toString()
			statOption.text = stat
			firstOption.text = first
			secondOption.text = second



		}

	}
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.list_card, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return itemList.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {



	}



}