package com.example.pocketloa.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pocketloa.R
import com.example.pocketloa.databinding.ActivityMainBinding
import com.example.pocketloa.view.auction.AuctionRVActivity
import com.example.pocketloa.view.auction.AuctionSelectActivity

class MainActivity : AppCompatActivity() {

	// 뒤로가기
	private var doubleBackToExit = false
	private lateinit var binding : ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {

		installSplashScreen()

		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.mainAuctionButton.setOnClickListener {
			val intent = Intent(this, AuctionSelectActivity::class.java)
			startActivity(intent)
		}



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