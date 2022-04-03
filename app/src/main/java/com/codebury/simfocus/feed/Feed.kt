package com.codebury.simfocus.feed

import AddPackage.AddPost
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codebury.simfocus.R
import com.codebury.simfocus.chatmodule.PostData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Feed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        val actinBtn: FloatingActionButton = findViewById(R.id.add_fab)
        supportActionBar?.hide()
        val postRecycler: RecyclerView = findViewById(R.id.postRecycler)
        postRecycler.layoutManager = LinearLayoutManager(this)
        val adapter = PostAdapter(PostData)
        postRecycler.adapter = adapter
        actinBtn.setOnClickListener {
            val intent = Intent(applicationContext, AddPost::class.java)
            startActivity(intent)

        }
    }
}