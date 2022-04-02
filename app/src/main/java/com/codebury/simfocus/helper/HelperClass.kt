package com.codebury.simfocus.helper

import android.widget.ImageView
import com.bumptech.glide.Glide


fun loadImage(url : String?, uploadImage : ImageView){
    Glide.with(uploadImage).load(url).into(uploadImage)
}