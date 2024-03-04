package com.example.pocketloa.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketloa.R
import com.example.pocketloa.model.auction.res.Item

class AuctionAdapter(val context: Context, val itemList: List<Item>) : RecyclerView.Adapter<AuctionAdapter.ViewHolder>(){

	inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
		val name : TextView = view.findViewById(R.id.item_name)
		val grade : TextView = view.findViewById(R.id.grade)
		val gradeQuality : TextView = view.findViewById(R.id.grade_quality)
		val buyPrice : TextView = view.findViewById(R.id.buy_price)
		val stat : TextView = view.findViewById(R.id.stat_option)
		val firstAbility : TextView = view.findViewById(R.id.first_ability)
		val secondAbility : TextView = view.findViewById(R.id.second_ability)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_auction, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return itemList.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {

		holder.name.text = itemList[position].Name
		holder.grade.text = itemList[position].Grade
		holder.gradeQuality.text = itemList[position].GradeQuality.toString()
		holder.buyPrice.text = itemList[position].AuctionInfo.BuyPrice.toString()
		holder.stat.text = itemList[position].Options[0].OptionName + itemList[position].Options[0].Value
		holder.stat.text = itemList[position].Options[1].OptionName + itemList[position].Options[1].Value
		holder.stat.text = itemList[position].Options[2].OptionName + itemList[position].Options[2].Value

	}



}