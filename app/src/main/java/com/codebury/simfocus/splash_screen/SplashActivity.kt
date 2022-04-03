package com.codebury.simfocus.splash_screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.codebury.simfocus.R
import com.codebury.simfocus.authentication.AuthLoginScreen
import com.codebury.simfocus.main_activity.MainActivity


class SplashActivity : AppCompatActivity() {

    private lateinit var topAnimation: Animation
    private lateinit var bottomAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("kotlinsharedpreference",
            Context.MODE_PRIVATE)



        intent = if(sharedPreferences.getBoolean("isLogin",false)) {
            Intent(this,
                MainActivity::class.java)
        } else {
            Intent(this,
                AuthLoginScreen::class.java)
        }

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_up_to_bottom_splash)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_bottom_to_up_splash)

        val topPartLogo: ImageView = findViewById(R.id.logoSplash)
        val bottomPartTv: TextView = findViewById(R.id.motoSplash)

        topPartLogo.animation = topAnimation
        bottomPartTv.animation = bottomAnimation

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, 2000)

    }
}