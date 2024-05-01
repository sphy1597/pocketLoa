package com.example.pocketloa.view.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.HandlerCompat
import com.example.pocketloa.databinding.ActivityIntroBinding
import com.example.pocketloa.view.MainActivity

class IntroActivity : AppCompatActivity() {

	private lateinit var binding : ActivityIntroBinding
	private val viewModel : IntroViewModel by viewModels()
	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		binding = ActivityIntroBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val handler = HandlerCompat.createAsync(mainLooper)
		handler.postDelayed(
			{
				val intent = Intent(this, MainActivity::class.java)
				startActivity(intent)
				finish()
			}, SPLASH_DELAY_TIME
		)
	}
	companion object {
		private const val SPLASH_DELAY_TIME = 1000L
	}
}