package com.codebury.simfocus.authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codebury.simfocus.ModelData.LogInDataClass
import com.codebury.simfocus.ModelData.ReturnLogin
import com.codebury.simfocus.R
import com.codebury.simfocus.WebServices.ApiInterface
import com.codebury.simfocus.helper.endLoading
import com.codebury.simfocus.helper.startLoading
import com.codebury.simfocus.main_activity.MainActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_auth_login_screen.*
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

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("kotlinsharedpreference",
            Context.MODE_PRIVATE)

        val editor:SharedPreferences.Editor =  sharedPreferences.edit()



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

            val view: View? = this.currentFocus
            if (view != null) {
                val imm: InputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }

            startLoading(this)
            val signedUser = LogInDataClass(etUserName.text.toString(),etPassword.text.toString())
            Thread().run {
                retrofit.signInUser(signedUser).enqueue(object : Callback<LogInDataClass> {
                    override fun onResponse(
                        call: Call<LogInDataClass>,
                        response: Response<LogInDataClass>
                    ) {
                        Log.e("User", "New User")
                        editor.putBoolean("isLogin",true)
                        editor.apply()
                        val intent = Intent(this@AuthLoginScreen ,MainActivity::class.java)
                        startActivity(intent)
                        runOnUiThread {
                            endLoading()
                            finish()
                        }
                    }
                    override fun onFailure(call: Call<LogInDataClass>, t: Throwable) {
                        Log.e("User", "New User not added")
                        runOnUiThread {
                            endLoading()
                            Toast.makeText(this@AuthLoginScreen, "Login Unsuccessful!! Please Register", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }
}



