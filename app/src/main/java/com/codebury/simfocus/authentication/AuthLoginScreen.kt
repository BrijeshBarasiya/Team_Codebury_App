package com.codebury.simfocus.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.codebury.simfocus.ModelData.LogInDataClass
import com.codebury.simfocus.R
import com.google.android.material.textfield.TextInputEditText
import com.codebury.simfocus.WebServices.ApiInterface
import java.util.regex.Pattern
import kotlinx.android.synthetic.main.activity_auth_login_screen.btnSignIn
import kotlinx.android.synthetic.main.activity_auth_login_screen.etPassword
import kotlinx.android.synthetic.main.activity_auth_login_screen.etUserName
import kotlinx.android.synthetic.main.activity_auth_login_screen.tvDontHaveAnAccount
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthLoginScreen : AppCompatActivity() {
    lateinit var retrofit: ApiInterface
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
        tvDontHaveAnAccount.setOnClickListener {
            val intent = Intent(this, AuthSignUpScreen::class.java)
            startActivity(intent)
        }
        retrofit = ApiInterface.getRetrofitObject()
        btnSignIn.setOnClickListener {
            val signedUser = LogInDataClass(etUserName.text.toString(),etPassword.text.toString())
            Thread().run {
                retrofit.signInUser(signedUser).enqueue(object : Callback<LogInDataClass> {
                    override fun onResponse(
                        call: Call<LogInDataClass>,
                        response: Response<LogInDataClass>
                    ) {
                        Log.e("User", "New User")
                        val intent = Intent(this@AuthLoginScreen ,AuthSignUpScreen::class.java)
                        startActivity(intent)
                        runOnUiThread {
                            finish()
                        }
                    }
                    override fun onFailure(call: Call<LogInDataClass>, t: Throwable) {
                        Log.e("User", "New User not added")
                        runOnUiThread {
                            Toast.makeText(this@AuthLoginScreen, "Login Unsuccesfull", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }
}



