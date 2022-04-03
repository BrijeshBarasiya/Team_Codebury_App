package com.codebury.simfocus.fragments

import AddPackage.AddPost
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codebury.simfocus.R
import com.codebury.simfocus.chatmodule.PostData
import com.codebury.simfocus.feed.PostAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val actinBtn: FloatingActionButton = view.findViewById(R.id.add_fab)
        val postRecycler: RecyclerView = view.findViewById(R.id.postRecycler)
        postRecycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PostAdapter(PostData,this)
        postRecycler.adapter = adapter
        actinBtn.setOnClickListener {
            val intent = Intent(requireContext(), AddPost::class.java)
            requireContext().startActivity(intent)
        }
        return view
    }
}