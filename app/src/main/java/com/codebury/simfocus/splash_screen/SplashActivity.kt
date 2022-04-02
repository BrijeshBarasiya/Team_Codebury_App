package com.codebury.simfocus.splash_screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.codebury.simfocus.MainActivity
import com.codebury.simfocus.R
import com.codebury.simfocus.authentication.AuthLoginScreen
import com.codebury.simfocus.authentication.AuthSignUpScreen

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var topAnimation: Animation
    private lateinit var bottomAnimation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_up_to_bottom_splash)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_bottom_to_up_splash)

        val topPartLogo: ImageView = findViewById(R.id.logoSplash)
        val bottomPartTv: TextView = findViewById(R.id.motoSplash)

        topPartLogo.animation = topAnimation
        bottomPartTv.animation = bottomAnimation

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AuthSignUpScreen::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}