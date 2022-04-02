package com.codebury.simfocus.chatmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.codebury.simfocus.R

class ChatScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatmodule_chat_screen)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutChatScreen,ChatFragmentActivity()).commit()
    }
}