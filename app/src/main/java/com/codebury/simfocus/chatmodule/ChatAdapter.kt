package com.example.androidoverview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codebury.simfocus.R
import com.codebury.simfocus.chatmodule.ChatModelClass
import com.codebury.simfocus.helper.loadImage
import de.hdodenhof.circleimageview.CircleImageView

class ChatAdapter (private val mList: List<ChatModelClass>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsDetails = mList[position]
        holder.chatName.text = itemsDetails.chatName
        holder.chatMessage.text = itemsDetails.chatMessage
        holder.chatTime.text = itemsDetails.chatTime
        loadImage(itemsDetails.chatImage, holder.chatImage)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        val chatName : TextView = itemView.findViewById(R.id.chatName)
        val chatMessage : TextView = itemView.findViewById(R.id.chatMessage)
        val chatTime : TextView = itemView.findViewById(R.id.timeStamp)
        val chatImage : CircleImageView = itemView.findViewById(R.id.chatImg)
    }

}