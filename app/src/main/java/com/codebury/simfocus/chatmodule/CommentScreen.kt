package com.codebury.simfocus.chatmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codebury.simfocus.R

class CommentScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_screen)
        supportActionBar?.hide()
    }
}