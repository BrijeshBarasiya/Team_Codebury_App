package com.codebury.simfocus.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.codebury.simfocus.R

class UsersProfileScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_profile_screen)

        val adapter = ProfileAdapter(
            listOf("1"),this)
        val recyclerView = findViewById<RecyclerView>(R.id.profileRecycler)
        recyclerView.adapter = adapter

        supportActionBar?.hide()
    }
}