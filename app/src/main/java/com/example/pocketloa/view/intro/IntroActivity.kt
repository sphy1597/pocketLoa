package com.example.pocketloa.view.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pocketloa.R
import com.example.pocketloa.databinding.ActivityIntroBinding
import com.example.pocketloa.view.MainActivity
import timber.log.Timber

class IntroActivity : AppCompatActivity() {

	private lateinit var binding : ActivityIntroBinding
	private val viewModel : IntroViewModel by viewModels()
	override fun onCreate(savedInstanceState: Bundle?) {

		installSplashScreen()

		super.onCreate(savedInstanceState)
		binding = ActivityIntroBinding.inflate(layoutInflater)
		setContentView(binding.root)

		Timber.tag("test").d("onCreate IntroActivity")

		val intent = Intent(this, MainActivity::class.java)
		startActivity(intent)


	}
}