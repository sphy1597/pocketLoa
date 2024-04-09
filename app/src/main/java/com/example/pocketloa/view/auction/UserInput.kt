package com.example.pocketloa.view.auction

import android.os.Parcel
import android.os.Parcelable
import android.text.Editable

data class UserInput(
	val Category: String,
	val Grade: String,
	val Quality: String,
	val Stat1: String,
	val StatMin1: String,
	val Stat2: String,
	val StatMin2: String,
	val Engraving1: String,
	val EngravingMin1: String,
	val Engraving2: String,
	val EngravingMin2: String,
	val Penalty: String,
	val PenaltyMin: String
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString() ?: "",
		parcel.readString() ?: "",
		parcel.readString() ?: "",
		parcel.readString() ?: "",
		parcel.readString() ?: "",
		parcel.readString() ?: "",
		parcel.readString() ?: "",
		parcel.readString() ?: "",
		parcel.readString() ?: "",
		parcel.readString() ?: "",
		parcel.readString() ?: "",
		parcel.readString() ?: "",
		parcel.readString() ?: ""
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(Category)
		parcel.writeString(Grade)
		parcel.writeString(Quality)
		parcel.writeString(Stat1)
		parcel.writeString(StatMin1)
		parcel.writeString(Stat2)
		parcel.writeString(StatMin2)
		parcel.writeString(Engraving1)
		parcel.writeString(EngravingMin1)
		parcel.writeString(Engraving2)
		parcel.writeString(EngravingMin2)
		parcel.writeString(Penalty)
		parcel.writeString(PenaltyMin)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<UserInput> {
		override fun createFromParcel(parcel: Parcel): UserInput {
			return UserInput(parcel)
		}

		override fun newArray(size: Int): Array<UserInput?> {
			return arrayOfNulls(size)
		}
	}
}