package com.codebury.simfocus.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codebury.simfocus.R
import com.codebury.simfocus.helper.loadImage
import de.hdodenhof.circleimageview.CircleImageView

class CommentAdapter(private val commentList : List<CommentModelClass>) : RecyclerView.Adapter<CommentAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dialog_comment_ui, parent, false)
        return CommentAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commentDetails = commentList[position]
        holder.userName.text = commentDetails.commentUserName
        holder.comment.text = commentDetails.comment
        loadImage(commentDetails.commentUserImage,holder.commentImage)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        val comment : TextView = itemView.findViewById(R.id.tvcomment)
        val userName : TextView = itemView.findViewById(R.id.commentUserName)
        val commentImage : CircleImageView = itemView.findViewById(R.id.commentImage)
    }

}