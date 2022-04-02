package com.codebury.simfocus.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codebury.simfocus.R

class AuthSignUpScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_sign_up_screen)

        supportActionBar?.hide()
    }
}