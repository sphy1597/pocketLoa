package com.example.pocketloa.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.pocketloa.R

class MainActivity : AppCompatActivity() {

	// 뒤로가기
	private var doubleBackToExit = false
	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}


	/*
		back 버튼으로 introActivity로 이동 방지
		1.5초 안에 2번 누르면 앱 종료
	*/

	@SuppressLint("MissingSuperCall")
	override fun onBackPressed() {

		if (doubleBackToExit) {
			finishAffinity()
		} else {
			Toast.makeText(this, "종료하시려면 뒤로가기를 한번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
			doubleBackToExit = true
			runDelayed(1500L) {
				doubleBackToExit = false
			}
		}

	}


	private fun runDelayed(millis: Long, function: () -> Unit) {
		Handler(Looper.getMainLooper()).postDelayed(function, millis)
	}
}