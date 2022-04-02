package com.codebury.simfocus.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codebury.simfocus.R
import java.util.regex.Pattern

class AuthLoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_login_screen)

        supportActionBar?.hide()

    }
}