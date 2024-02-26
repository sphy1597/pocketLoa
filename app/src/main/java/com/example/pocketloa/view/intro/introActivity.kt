package com.example.pocketloa.view.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pocketloa.R
import timber.log.Timber

class introActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {

		installSplashScreen()

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_intro)

		Timber.tag("test").d("onCreate introActivty")
	}
}