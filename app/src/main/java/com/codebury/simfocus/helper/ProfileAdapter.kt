package com.codebury.simfocus.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.codebury.simfocus.R

class ProfileAdapter(private val mList : List<String>, val context: Context) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.all_post_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ViewHolder, position: Int) {
        loadImage(mList[position],holder.image)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView : View) :  RecyclerView.ViewHolder(ItemView){
        val image: ImageView = itemView.findViewById(R.id.allPosts)
    }
}