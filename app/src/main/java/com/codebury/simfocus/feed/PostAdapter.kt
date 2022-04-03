package com.codebury.simfocus.feed

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.codebury.simfocus.R
import com.codebury.simfocus.chatmodule.PostModelClass
import com.codebury.simfocus.helper.loadImage
import de.hdodenhof.circleimageview.CircleImageView


class PostAdapter(private val PostList: List<PostModelClass>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_design, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val PostDetails = PostList[position]
        holder.userName.text = PostDetails.userName
        holder.message.text = PostDetails.message
        loadImage(PostDetails.profile, holder.profile)
        loadImage(PostDetails.Post, holder.Post)

        var flag = 0
        holder.imgPost.setOnClickListener(object : DoubleClickListener() {
            override fun onDoubleClick(v: View?) {
                holder.imgLike.animate().apply {
                    duration = 1000
                    rotationYBy(360f)
                }.start()
                if (flag == 0) {
                    holder.imgLike.setImageResource(R.drawable.ic_baseline_liked_24)
                    flag = 1
                } else if (flag == 1) {
                    holder.imgLike.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    flag = 0;
                }
            }
        })
        holder.imgLike.setOnClickListener {
            holder.imgLike.animate().apply {
                duration = 1000
                rotationYBy(360f)
            }.start()
            if (flag == 0) {
                holder.imgLike.setImageResource(R.drawable.ic_baseline_liked_24)
                flag = 1
            } else if (flag == 1) {
                holder.imgLike.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                flag = 0;
            }
        }
    }

    abstract class DoubleClickListener : View.OnClickListener {
        var lastClickTime: Long = 0
        override fun onClick(v: View?) {
            val clickTime = System.currentTimeMillis()
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                onDoubleClick(v)
            }
            lastClickTime = clickTime
        }

        abstract fun onDoubleClick(v: View?)

        companion object {
            private const val DOUBLE_CLICK_TIME_DELTA: Long = 300 //milliseconds
        }
    }

    override fun getItemCount(): Int {
        return PostList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val profile: CircleImageView = itemView.findViewById(R.id.profile)
        val userName: TextView = itemView.findViewById(R.id.userName)
        val message: TextView = itemView.findViewById(R.id.message)
        val Post: ImageButton = itemView.findViewById(R.id.Post)
        val imgPost: ImageButton = itemView.findViewById(R.id.Post)
        val imgLike: ImageButton = itemView.findViewById(R.id.likeBtn)
    }

}