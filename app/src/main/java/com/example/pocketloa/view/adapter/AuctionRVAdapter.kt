package com.example.pocketloa.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pocketloa.R
import com.example.pocketloa.model.auction.res.Item
import org.w3c.dom.Text


class AuctionRVAdapter(val context: Context, private val itemList: List<Item>) : RecyclerView.Adapter<AuctionRVAdapter.ViewHolder>(){

	inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
		private val itemName : TextView = itemView.findViewById(R.id.auction_list_name)
		private val gradeQuality : TextView = itemView.findViewById(R.id.auction_list_gradeQuality)
		private val buyPrice : TextView = itemView.findViewById(R.id.auction_list_buyPrice)
		private val statOption : TextView = itemView.findViewById(R.id.auction_list_stat)
		private val firstOption : TextView = itemView.findViewById(R.id.auction_list_first)
		private val secondOption : TextView = itemView.findViewById(R.id.auction_list_second)
		private val penaltyOption : TextView = itemView.findViewById(R.id.auction_list_penalty)
		private val allowTrade : TextView = itemView.findViewById(R.id.auction_list_allowTrade)
		private val bidPrice : TextView = itemView.findViewById(R.id.auction_list_bidPrice)
		private val iconImage : ImageView = itemView.findViewById(R.id.auction_list_image)
		fun bind(item: Item){

			var stat = ""
			var penalty = ""
			var ability = mutableListOf<String>()

			for ( option in item.Options ){
				when(option.Type){
					"STAT" -> {
						stat = stat + " "+ option.OptionName + " " + option.Value
					}
					"ABILITY_ENGRAVE" -> {
						if (option.IsPenalty){
							penalty = option.OptionName + " " + option.Value
						}else{
							var str = "[${option.OptionName}] ${option.Value}"
							ability.add(str)
						}
					}

				}

			}
			if(item.Grade == "고대"){
				itemName.setTextColor(Color.parseColor("#C9A472"))
			}else if (item.Grade == "유물"){
				itemName.setTextColor(Color.parseColor("#FF6000"))
			}

			itemName.text = item.Name
			gradeQuality.text = "품질 " + item.GradeQuality.toString()
			buyPrice.text = "즉시구매가 " + item.AuctionInfo.BuyPrice.toString()
			bidPrice.text = "경매최소가 " + item.AuctionInfo.BidStartPrice.toString()
			statOption.text = stat
			penaltyOption.text = penalty
			firstOption.text = ability[0]
			secondOption.text = ability[1]
			allowTrade.text = "${item.AuctionInfo.TradeAllowCount.toString()}회 거래 가능"
			Glide.with(itemView).load(item.Icon).into(iconImage)



		}

	}
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.auction_item, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return itemList.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(itemList[position])
	}



}