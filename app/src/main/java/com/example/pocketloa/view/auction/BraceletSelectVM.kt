package com.example.pocketloa.view.auction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BraceletSelectVM : ViewModel() {

	private var fixedMax = 2
	private var grantMax = 2
	private var fixedCount = 0
	private var grantCount = 0

	fun setBraceletGrade(grade: String) {
		when (grade) {
			"유물" -> {
				grantMax = 2
			}
			"고대" -> {
				grantMax = 3
			}

		}
	}

	private val _fixedLiveData = MutableLiveData<Int>()
	val fixed: LiveData<Int>
		get() = _fixedLiveData

	private val _grantLiveData = MutableLiveData<Int>()
	val grant: LiveData<Int>
		get() = _grantLiveData

	fun fixedSubtract() {
		if (fixedCount > 0) {
			fixedCount -= 1
			_fixedLiveData.postValue(fixedCount)
			Log.d("test", "fixed : $fixedCount")
		}

	}

	fun fixedPlus() {
		if (fixedCount < fixedMax) {
			fixedCount += 1
			_fixedLiveData.postValue(fixedCount)
			Log.d("test", "fixed : $fixedCount")
		}
	}

	fun grantSubtract() {
		if (grantCount > 0) {
			grantCount -= 1
			_grantLiveData.postValue(grantCount)
			Log.d("test", "fixed : $grantCount")
		}
	}

	fun grantPlus() {
		if (grantCount < grantMax) {
			grantCount += 1
			_grantLiveData.postValue(grantCount)
			Log.d("test", "fixed : $grantCount")
		}
	}


}