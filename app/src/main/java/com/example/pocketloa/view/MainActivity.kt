package com.example.pocketloa.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pocketloa.R
import com.example.pocketloa.databinding.ActivityMainBinding
import com.example.pocketloa.view.auction.AuctionFragment
import java.lang.Math.ceil

class MainActivity : AppCompatActivity() {

	// 뒤로가기
	private var doubleBackToExit = false
	private lateinit var binding : ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		initNav()



		binding.bottomNav.setOnItemSelectedListener {
			when(it.itemId){
				R.id.bottom_nav_auction -> replaceFragment(AuctionFragment(), "AUCTION")
				R.id.bottom_nav_home -> replaceFragment(HomeFragment(), "POCKETLOA")
				R.id.bottom_nav_menu -> replaceFragment(MenuFragment(), "MENU")
				else -> {
					Log.d("test", "bottom nav when")

				}

			}
			true
		}


	}
	private fun replaceFragment(fragment: Fragment, title : String){
		val fragmentManager = supportFragmentManager
		val fragmentTransaction = fragmentManager.beginTransaction()
		fragmentTransaction.replace(R.id.main_frame_layout, fragment)
		fragmentTransaction.commit()
		binding.toolbarMain.toolbarTitle.text = title

	}

	// 시작하면 홈 화면 선택
	private fun initNav(){
		// set tool bar title
		replaceFragment(HomeFragment(), "POCKETLOA")
		binding.bottomNav.selectedItemId = R.id.bottom_nav_home
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