package com.codebury.simfocus.chatmodule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codebury.simfocus.R
import com.example.androidoverview.ChatAdapter

class ChatFragmentActivity : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.chatmodule_chat_activity, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val simformRecyler = view.findViewById<RecyclerView>(R.id.simformRecycler)
        simformRecyler.layoutManager = LinearLayoutManager(context)
        val adapter = ChatAdapter(chatDetails)
        simformRecyler.adapter = adapter

    }

}