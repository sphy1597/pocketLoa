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
import com.example.pocketloa.databinding.AuctionItemBinding
import com.example.pocketloa.model.auction.res.Item
import org.w3c.dom.Text


class AuctionRVAdapter(val context: Context, private var itemList: List<Item>) :
	RecyclerView.Adapter<AuctionRVAdapter.Holder>() {

	inner class Holder(val binding: AuctionItemBinding) : RecyclerView.ViewHolder(binding.root) {
		val itemName = binding.auctionListName
		val gradeQuality = binding.auctionListGradeQuality
		val buyPrice = binding.auctionListBuyPrice
		val statOption = binding.auctionListStat
		val firstOption = binding.auctionListFirst
		val secondOption = binding.auctionListSecond
		val penaltyOption = binding.auctionListPenalty
		val allowTrade = binding.auctionListAllowTrade
		val bidPrice = binding.auctionListBidPrice
		val iconImage = binding.auctionListImage

		fun bind(item: Item) {

			var stat = ""
			var penalty = ""
			var ability = mutableListOf<String>()

			for (option in item.Options) {
				when (option.Type) {
					"STAT" -> {
						stat = stat + " " + option.OptionName + " " + option.Value
					}
					"ABILITY_ENGRAVE" -> {
						if (option.IsPenalty) {
							penalty = option.OptionName + " " + option.Value
						} else {
							var str = "[${option.OptionName}] ${option.Value}"
							ability.add(str)
						}
					}

				}

			}
			if (item.Grade == "고대") {
				itemName.setTextColor(Color.parseColor("#C9A472"))
			} else if (item.Grade == "유물") {
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


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuctionRVAdapter.Holder {
		val binding = AuctionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return Holder(binding)
	}

	override fun getItemCount(): Int {
		return itemList.size
	}

	override fun onBindViewHolder(holder: AuctionRVAdapter.Holder, position: Int) {
		holder.bind(itemList[position])
	}

	fun updateItems(newItems: List<Item>) {
		itemList = newItems
		notifyDataSetChanged()
	}


}