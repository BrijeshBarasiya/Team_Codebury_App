package com.codebury.simfocus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.codebury.simfocus.R
import com.codebury.simfocus.dataclass.GridImageDataClass
import com.codebury.simfocus.helper.loadImage
import com.squareup.picasso.Picasso

class GridfrecyclerImageAdapter(val itemList: ArrayList<GridImageDataClass>): RecyclerView.Adapter<GridfrecyclerImageAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridfrecyclerImageAdapter.MyViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.gridrecycler_layout, parent, false)
        return MyViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: GridfrecyclerImageAdapter.MyViewHolder, position: Int) {
        loadImage(itemList[position].images,holder.itemImg)
    }

    override fun getItemCount(): Int {
        return  itemList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemImg: ImageView = itemView.findViewById(R.id.gridRecyclerImage)
    }
}