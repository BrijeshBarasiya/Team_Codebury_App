package com.codebury.simfocus.chatmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codebury.simfocus.R
import com.codebury.simfocus.feed.CommentAdapter
import com.codebury.simfocus.feed.commentDetails

class CommentScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_screen)
        supportActionBar?.hide()

        val commentRecycler: RecyclerView = findViewById(R.id.commentRecycler)
        commentRecycler.layoutManager = LinearLayoutManager(this)
        val adapter = CommentAdapter(commentDetails)
        commentRecycler.adapter = adapter
    }
}