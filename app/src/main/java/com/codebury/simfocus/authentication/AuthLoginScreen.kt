package com.codebury.simfocus.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.codebury.simfocus.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_auth_login_screen.*
import java.util.regex.Pattern

class AuthLoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_login_screen)
        var view = false

        supportActionBar?.hide()

        btnSignIn.setOnClickListener {
            val intent = Intent(this, AuthSignUpScreen::class.java)
            startActivity(intent)
            Toast.makeText(this, etUserName.text, Toast.LENGTH_SHORT).show() }

        val etUserName: TextInputEditText = findViewById(R.id.etUserName)

        etUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(target: Editable?) {
                if(target.toString().isEmpty()) {
                    etUserName.error = getString(R.string.emptyUsernameError)
                }
            }
        })

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(target: Editable?) {
                if(target.toString().isEmpty()) {
                    etPassword.error = getString(R.string.invalidPasswordError)
                } else if(target.toString().length < 8) {
                    etPassword.error = getString(R.string.password_length)
                }
            }
        })
    }
}